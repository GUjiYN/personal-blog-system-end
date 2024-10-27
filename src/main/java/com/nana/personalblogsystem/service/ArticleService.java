package com.nana.personalblogsystem.service;

import com.nana.personalblogsystem.model.entity.ArticleDO;
import com.nana.personalblogsystem.model.vo.ArticleVO;
import jakarta.servlet.http.HttpServletRequest;

/**
 * @version v1.0.0
 * @since v1.0.0
 * @author nana
 */

public interface ArticleService {

    void createArticle(ArticleVO articleVO, HttpServletRequest request);

    void updateArticle(ArticleVO article, String aid);

    void deleteArticle(String aid);
}
