package com.nana.personalblogsystem.mapper;

import com.nana.personalblogsystem.model.entity.ArticleDO;
import com.nana.personalblogsystem.model.entity.UserDO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * 文章映射器
 * <p>
 * @version v1.0.0
 * @since v1.0.0
 * @author nana
 */
@Mapper
public interface ArticleMapper {


    @Select("SELECT * FROM na_article WHERE title = #{title} OR desc = #{desc}")
    ArticleDO articleExist(String title, String desc);

    @Insert("INSERT INTO na_article (aid, authid, title, tags, [desc]) VALUES (#{aid}, #{authid}, #{title}, #{tags}, #{desc})")
    void createArticle(ArticleDO articleDO);

    @Update("UPDATE na_article SET title = #{title}, desc = #{desc}, tags = #{tags} WHERE aid = #{aid}")
    void updateArticle(ArticleDO articleDO);
}
