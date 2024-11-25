package com.nana.personalblogsystem.service;

import com.nana.personalblogsystem.model.CustomPage;
import com.nana.personalblogsystem.model.dto.CommentDTO;
import com.nana.personalblogsystem.model.entity.CommentDO;
import com.nana.personalblogsystem.model.vo.CommentVO;

public interface CommentService {
    CustomPage<CommentDTO> getCommentList(Integer page, Integer size, String aid);

    CommentDO addComment(CommentVO commentVO);

    void deleteComment(String cid);
}
