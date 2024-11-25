package com.nana.personalblogsystem.controller;


import com.nana.personalblogsystem.model.CustomPage;
import com.nana.personalblogsystem.model.dto.CommentDTO;
import com.nana.personalblogsystem.model.dto.InfoDTO;
import com.nana.personalblogsystem.model.dto.TokenDTO;
import com.nana.personalblogsystem.model.entity.CommentDO;
import com.nana.personalblogsystem.model.vo.CommentVO;
import com.nana.personalblogsystem.service.CommentService;
import com.nana.personalblogsystem.service.InfoService;
import com.nana.personalblogsystem.service.TokenService;
import com.xlf.utility.BaseResponse;
import com.xlf.utility.ErrorCode;
import com.xlf.utility.ResultUtil;
import com.xlf.utility.exception.BusinessException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 评论控制器
 * <hr/>
 * 评论控制器，用于评论的增删改查
 * @version v1.0.0
 * @since v1.0.0
 * @author nana
 */

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comment")
public class CommentController {
    private final CommentService commentService;
    private final TokenService tokenService;
    private final InfoService infoService;

    /**
     * 获取文章评论
     * <p>
     *  用于获取评论列表
     */
/*
  @GetMapping("/list")
   public @NotNull ResponseEntity<BaseResponse<CustomPage<CommentDTO>>> getCommentList(
           @RequestParam(value = "page", defaultValue = "1") Integer page,
           @RequestParam(value = "size", defaultValue = "20") Integer size,
           @RequestParam("aid") String aid
   ) {
       CustomPage<CommentDTO> commentList = commentService.getCommentList(page, size, aid);

       return ResultUtil.success("操作成功", commentList);
   }
*/

    @GetMapping("/list")
    public @NotNull ResponseEntity<BaseResponse<CustomPage<CommentDTO>>> getCommentList(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "size", defaultValue = "20") Integer size,
            @RequestParam("aid") String aid
    ) {
        // 获取所有评论数据
        CustomPage<CommentDTO> commentList = commentService.getCommentList(page, size, aid);

        // 构建嵌套的评论树结构
        List<CommentDTO> topLevelComments = buildCommentTree(commentList.getRecords());

        // 更新分页数据的 records 为嵌套结构
        commentList.setRecords(topLevelComments);

        return ResultUtil.success("操作成功", commentList);
    }

    /**
     * 构建嵌套的评论树结构
     *
     * @param comments 扁平化的评论列表
     * @return 嵌套的评论树
     */
    private List<CommentDTO> buildCommentTree(List<CommentDTO> comments) {
        // 创建 Map，将所有回复按父评论 ID 分组
        Map<String, List<CommentDTO>> replyMap = comments.stream()
                .filter(comment -> comment.getReplyId() != null) // 筛选出所有回复
                .collect(Collectors.groupingBy(CommentDTO::getReplyId));

        // 筛选出顶级评论（replyid 为 null 的评论）
        List<CommentDTO> topLevelComments = comments.stream()
                .filter(comment -> comment.getReplyId() == null)
                .collect(Collectors.toList());

        // 递归填充子回复
        for (CommentDTO comment : topLevelComments) {
            fillReplies(comment, replyMap);
        }

        return topLevelComments;
    }

    /**
     * 递归填充子回复
     *
     * @param comment  当前评论
     * @param replyMap 回复的 Map（按父评论 ID 分组）
     */
    private void fillReplies(CommentDTO comment, Map<String, List<CommentDTO>> replyMap) {
        List<CommentDTO> replies = replyMap.get(comment.getCid());
        if (replies != null) {
            for (CommentDTO reply : replies) {
                fillReplies(reply, replyMap); // 递归填充子回复
            }
            comment.setReplies(replies); // 设置子回复
        }
    }


    /**
     * 增加评论
     * <p>
     *  用于增加评论
     */
    @Transactional
    @PostMapping("/add")
    public  ResponseEntity<BaseResponse<CommentDO>> addComment(
            @RequestBody @Validated CommentVO commentVO
    ) {
        // 打印接收到的 JSON 数据
        log.info("接收到的评论数据：{}", commentVO);
        CommentDO newComment = commentService.addComment(commentVO);
        return ResultUtil.success("发布成功", newComment);
    }


    /**
     * 删除评论
     * <p>
     *  用于删除评论
     */
    @DeleteMapping("/delete")
    public ResponseEntity<BaseResponse<Void>> deleteComment(
        @RequestParam("cid") String cid,
        HttpServletRequest request
    ){
      if(cid.isBlank()){
          throw new BusinessException("评论id不能为空", ErrorCode.BODY_ERROR);
      }
      String token = request.getHeader("Authorization");
      TokenDTO tokenDTO = tokenService.selectToken(token);
      InfoDTO infoDTO = infoService.selectInfoByKey("system_super_admin_uuid");
      if (tokenDTO.getUserUuid().equals(infoDTO.getIvalue())) {
          commentService.deleteComment(cid);
          return ResultUtil.success("删除成功");
      }
      throw new BusinessException("无权限", ErrorCode.BODY_ERROR);
    }
}
