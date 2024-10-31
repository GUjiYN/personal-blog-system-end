package com.nana.personalblogsystem.mapper;

import com.nana.personalblogsystem.model.entity.ArticleDO;
import org.apache.ibatis.annotations.*;

/**
 * 文章映射器
 * <p>
 * @version v1.0.0
 * @since v1.0.0
 * @author nana
 */
@Mapper
public interface ArticleMapper {

    @Select("SELECT * FROM na_article WHERE aid = #{aid}")
    ArticleDO getArticleByAid(String aid);

    @Select("SELECT * FROM na_article WHERE title = #{title}")
    ArticleDO articleRepeat(String title, String desc);
    
    @Select("SELECT * FROM na_article WHERE aid = #{aid}")
    ArticleDO articleExist(String aid);

    @Insert("INSERT INTO na_article (aid, authid, title, tags, description, image) VALUES (#{aid}, #{authid}, #{title}, #{tags}, #{description}, #{image})")
    void createArticle(ArticleDO articleDO);

    @Update("UPDATE na_article SET title = #{title}, description = #{description}, tags = #{tags}, image = #{image} WHERE aid = #{aid}")
    void updateArticle(ArticleDO articleDO);

    @Delete("DELETE FROM na_article WHERE aid = #{aid}")
    void deleteArticle(String aid);
}
