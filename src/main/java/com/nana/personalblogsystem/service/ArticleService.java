package com.nana.personalblogsystem.service;

import com.nana.personalblogsystem.model.CustomPage;
import com.nana.personalblogsystem.model.dto.ArticleDTO;
import com.nana.personalblogsystem.model.vo.ArticleVO;
import jakarta.servlet.http.HttpServletRequest;

/**
 * @version v1.0.0
 * @since v1.0.0
 * @author nana
 */

public interface ArticleService {

    CustomPage<ArticleDTO> getArticleList(Integer page, Integer size);

    CustomPage<ArticleDTO> getArticlesByTitleOrDesc(String keyword, Integer page, Integer size);

    CustomPage<ArticleDTO> getArticlesByTags(String tags, Integer page, Integer size);

    ArticleDTO getArticleDetails(String aid);

    void createArticle(ArticleVO articleVO, HttpServletRequest request);

    void updateArticle(ArticleVO article, String aid);

    void deleteArticle(String aid);
}
