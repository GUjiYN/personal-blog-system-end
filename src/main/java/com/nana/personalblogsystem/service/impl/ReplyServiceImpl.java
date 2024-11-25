package com.nana.personalblogsystem.service.impl;

import com.nana.personalblogsystem.mapper.ReplyMapper;
import com.nana.personalblogsystem.model.entity.CommentDO;
import com.nana.personalblogsystem.model.entity.ReplyDO;
import com.nana.personalblogsystem.model.vo.ReplyVO;
import com.nana.personalblogsystem.service.ReplyService;
import com.xlf.utility.util.UuidUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

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
public class ReplyServiceImpl implements ReplyService {

    public final ReplyMapper replyMapper;

    @Override
    public ReplyDO addReply(ReplyVO replyVO) {
        ReplyDO newReply = new ReplyDO();
        newReply
                .setAid(replyVO.getAid())
                .setCid(UuidUtil.generateStringUuid())
                .setCname(replyVO.getCname())
                .setEmail(replyVO.getEmail())
                .setCdesc(replyVO.getCdesc())
                .setCreatedAt(new Timestamp(System.currentTimeMillis()))
                .setReplyid(replyVO.getReplyid());
        replyMapper.addReply(newReply);
        return newReply;
    }
}
