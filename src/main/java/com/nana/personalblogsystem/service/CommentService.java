package com.nana.personalblogsystem.service;

import com.nana.personalblogsystem.model.entity.CommentDO;

public interface CommentService {
    void addComment(CommentDO commentDO);

    void deleteComment(CommentDO commentDO);
}
