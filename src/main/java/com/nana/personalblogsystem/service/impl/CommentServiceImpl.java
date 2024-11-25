package com.nana.personalblogsystem.service.impl;

import com.nana.personalblogsystem.mapper.CommentMapper;
import com.nana.personalblogsystem.model.CustomPage;
import com.nana.personalblogsystem.model.dto.CommentDTO;
import com.nana.personalblogsystem.model.entity.CommentDO;
import com.nana.personalblogsystem.model.vo.CommentVO;
import com.nana.personalblogsystem.service.CommentService;
import com.nana.personalblogsystem.util.CopyUtil;
import com.xlf.utility.ErrorCode;
import com.xlf.utility.exception.BusinessException;
import com.xlf.utility.util.UuidUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * 评论逻辑
 * <hr/>
 * 评论逻辑，包含评论相关逻辑
 *
 * @author nana
 * @version v1.0.0
 * @since v1.0.0
 */
@Slf4j
@Service
@RequiredArgsConstructor

public class CommentServiceImpl implements CommentService {

   public final  CommentMapper commentMapper;


    /*@Override
    public CustomPage<CommentDTO> getCommentList(Integer page, Integer size, String aid) {
       List<CommentDO> commentList = commentMapper.getCommentList(aid);
        CustomPage<CommentDTO> customPage = new CustomPage<>();
        CopyUtil.pageDoCopyToDTO(commentList, customPage, Long.valueOf(page), Long.valueOf(size), CommentDTO.class);
       return customPage;
    }
*/

    @Override
    public CustomPage<CommentDTO> getCommentList(Integer page, Integer size, String aid) {
        // 1. 查询所有评论（包括顶级评论和回复）
        List<CommentDO> commentList = commentMapper.getCommentList(aid);

        // 2. 转换为 DTO
        List<CommentDTO> commentDTOList = commentList.stream()
                .map(comment -> {
                    CommentDTO dto = new CommentDTO();
                    BeanUtils.copyProperties(comment, dto);
                    return dto;
                })
                .collect(Collectors.toList());

        // 3. 构建评论树结构
        Map<String, List<CommentDTO>> replyMap = commentDTOList.stream()
                .filter(comment -> comment.getReplyId() != null) // 筛选出回复
                .collect(Collectors.groupingBy(CommentDTO::getReplyId));

        List<CommentDTO> topLevelComments = commentDTOList.stream()
                .filter(comment -> comment.getReplyId() == null) // 筛选出顶级评论
                .collect(Collectors.toList());

        // 递归填充子评论
        for (CommentDTO topComment : topLevelComments) {
            fillReplies(topComment, replyMap);
        }

        // 4. 分页处理顶级评论
        int fromIndex = Math.min((page - 1) * size, topLevelComments.size());
        int toIndex = Math.min(fromIndex + size, topLevelComments.size());
        List<CommentDTO> paginatedComments = topLevelComments.subList(fromIndex, toIndex);

        // 5. 设置分页数据
        CustomPage<CommentDTO> customPage = new CustomPage<>();
        customPage.setRecords(paginatedComments); // 设置当前页数据
        customPage.setTotal((long) topLevelComments.size()); // 设置总记录数
        customPage.setCurrent(Long.valueOf(page)); // 设置当前页
        customPage.setSize(Long.valueOf(size)); // 设置每页大小

        return customPage;
    }

    // 辅助方法：递归填充子评论
    private void fillReplies(CommentDTO comment, Map<String, List<CommentDTO>> replyMap) {
        List<CommentDTO> replies = replyMap.get(comment.getCid());
        if (replies != null) {
            for (CommentDTO reply : replies) {
                fillReplies(reply, replyMap); // 递归填充子回复
            }
            comment.setReplies(replies); // 设置子回复
        }
    }



    @Override
    public CommentDO addComment(CommentVO commentVO) {
        CommentDO newComment = new CommentDO();
        newComment
                .setAid(commentVO.getAid())
                .setCid(UuidUtil.generateStringUuid())
                .setCname(commentVO.getCname())
                .setEmail(commentVO.getEmail())
                .setCdesc(commentVO.getCdesc())
                .setCreatedAt(new Timestamp(System.currentTimeMillis()));
        commentMapper.addComment(newComment);
        return newComment;
    }




    @Override
    public void deleteComment(String cid) {
        CommentDO getComment = commentMapper.commentExist(cid);
        if (getComment == null) {
            throw new BusinessException("评论不存在", ErrorCode.EXISTED);
        }
        commentMapper.deleteComment(cid);
    }
}
