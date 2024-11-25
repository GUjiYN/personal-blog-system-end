package com.nana.personalblogsystem.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.sql.Timestamp;

/**
 * @version v1.0.0
 * @since v1.0.0
 * @author nana
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class ArticleDTO {
    /**
     * 文章表主键
     */
    private String aid;
    /**
     *作者id
     */
    private String authid;
    /**
     * 标题
     */
    private String title;
    /**
     * 标签
     */
    private String tags;
    /**
     * 内容
     */
    private String description;
    /**
     *图片
     */
    private String image;
    /**
     * 创建时间
     */
    private Timestamp createdAt;
    /**
     * 修改时间
     */
    private Timestamp updatedAt;
}
