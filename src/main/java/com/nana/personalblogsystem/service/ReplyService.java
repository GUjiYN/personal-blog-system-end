package com.nana.personalblogsystem.service;

import com.nana.personalblogsystem.model.entity.CommentDO;
import com.nana.personalblogsystem.model.entity.ReplyDO;
import com.nana.personalblogsystem.model.vo.ReplyVO;

public interface ReplyService {
    ReplyDO addReply(ReplyVO replyVO);
}
