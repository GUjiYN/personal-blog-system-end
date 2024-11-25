package com.nana.personalblogsystem.mapper;

import com.nana.personalblogsystem.model.entity.ArticleDO;
import com.nana.personalblogsystem.model.entity.CommentDO;
import org.apache.ibatis.annotations.*;

import javax.xml.stream.events.Comment;
import java.util.List;

@Mapper
public interface CommentMapper {
    /**
     * 获取评论列表
     * @return
     */
    @Select("SELECT * FROM na_comment WHERE aid = #{aid} ORDER BY created_at DESC")
    List<CommentDO> getCommentList(String aid);

    /**
     * 发布评论
     * @param commentDO
     */
    @Insert("INSERT INTO na_comment(aid, cid, cname, cdesc) VALUES (#{aid}, #{cid}, #{cname}, #{cdesc})")
    void addComment(CommentDO commentDO);

    /**
     * 查询评论是否存在
     * @param cid
     * @returnd
     */
    @Select("SELECT * FROM na_comment WHERE cid = #{cid}")
    CommentDO commentExist(String cid);

    /**
     * 删除评论
     * @param cid
     */
    @Delete("DELETE FROM na_comment WHERE cid = #{cid}")
    void deleteComment(String cid);
}
