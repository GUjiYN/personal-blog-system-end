package com.nana.personalblogsystem.service;

import com.nana.personalblogsystem.model.entity.ArticleDO;
import com.nana.personalblogsystem.model.vo.ArticleVO;

/**
 * @version v1.0.0
 * @since v1.0.0
 * @author nana
 */

public interface ArticleService {

    void createArticle(String title, String desc, String tags);

    void updateArticle(ArticleVO article, String aid);
}
