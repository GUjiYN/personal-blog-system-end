package com.nana.personalblogsystem.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nana.personalblogsystem.model.CustomPage;
import com.nana.personalblogsystem.model.entity.ArticleDO;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 文章映射器
 * <p>
 * @version v1.0.0
 * @since v1.0.0
 * @author nana
 */
@Mapper
public interface ArticleMapper {

    /**
     * 获取所有文章
     * @return
     */
    @Select("SELECT * FROM na_article ORDER BY created_at DESC")
    List<ArticleDO> getAllArticles();


    /**
     * 根据文章名字或内容模糊查询文章
     * @param keyword
     * @return
     */
    @Select("SELECT * FROM na_article WHERE title LIKE CONCAT('%', #{keyword}, '%') OR description LIKE CONCAT('%', #{keyword}, '%')")
    List<ArticleDO> getArticlesByTitleOrDesc(String keyword);


    /**
     * 根据标签查询文章
     * @param tags
     * @return
     */
    @Select("SELECT * FROM na_article WHERE tags LIKE CONCAT('%', #{tag}, '%')")
    List<ArticleDO> getArticlesByTags(String tags);


    /**
     * 根据文章id获取文章信息
     * @param aid
     * @return
     */
    @Select("SELECT * FROM na_article WHERE aid = #{aid}")
    ArticleDO getArticleDetails(String aid);


    /**
     * 检验文章是否重复
     * @param title
     * @param desc
     * @return
     */
    @Select("SELECT * FROM na_article WHERE title = #{title}")
    ArticleDO articleRepeat(String title, String desc);

    /**
     * 检验文章是否存在
     * @param aid
     * @return
     */
    @Select("SELECT * FROM na_article WHERE aid = #{aid}")
    ArticleDO articleExist(String aid);

    /**
     * 创建文章
     * @param articleDO
     */
    @Insert("INSERT INTO na_article (aid, authid, title, tags, description, image) VALUES (#{aid}, #{authid}, #{title}, #{tags}, #{description}, #{image})")
    void createArticle(ArticleDO articleDO);

    /**
     * 更新文章
     * @param articleDO
     */
    @Update("UPDATE na_article SET title = #{title}, description = #{description}, tags = #{tags}, image = #{image} WHERE aid = #{aid}")
    void updateArticle(ArticleDO articleDO);

    /**
     * 删除文章
     * @param aid
     */
    @Delete("DELETE FROM na_article WHERE aid = #{aid}")
    void deleteArticle(String aid);
}
