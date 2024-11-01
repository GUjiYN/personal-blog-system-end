package com.nana.personalblogsystem.mapper;

import com.nana.personalblogsystem.model.entity.CommentDO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import javax.xml.stream.events.Comment;
import java.util.List;

@Mapper
public interface CommentMapper {

    @Select("SELECT * FROM na_comment")
    List getAllComment();

    @Insert("INSERT INTO na_comment(cin, cname, cdesc, created_at, reply) VALUES (#{cin}, #{cname}, #{cdesc}. #{created_at}, #{reply})")
    void addComment(CommentDO commentDO);

    @Delete("DELETE FROM na_comment WHERE cin = #{cin}")
    void deleteComment(CommentDO commentDO);
}
