package com.nana.personalblogsystem.service.impl;

import com.google.gson.Gson;
import com.nana.personalblogsystem.mapper.ArticleMapper;
import com.nana.personalblogsystem.model.dto.TokenDTO;
import com.nana.personalblogsystem.model.entity.ArticleDO;
import com.nana.personalblogsystem.model.vo.ArticleVO;
import com.nana.personalblogsystem.service.ArticleService;
import com.xlf.utility.ErrorCode;
import com.xlf.utility.exception.BusinessException;
import com.xlf.utility.util.UuidUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

/**
 * 文章逻辑
 * <hr/>
 * 文章逻辑，包含文章相关逻辑
 *
 * @author nana
 * @version v1.0.0
 * @since v1.0.0
 */
@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {

    private final ArticleMapper articleMapper;
    private final TokenService tokenService;
    private final Gson gson;


    @Override
    public void createArticle(ArticleVO articleVO, HttpServletRequest request) {
        // 检查文章是否已存在
        ArticleDO getArticle = articleMapper.articleRepeat(articleVO.getTitle(), articleVO.getDesc());
        if (getArticle != null) {
            throw new BusinessException("文章名已存在", ErrorCode.EXISTED);
        }
        String token = request.getHeader("Authorization");
        TokenDTO tokenDTO = tokenService.selectToken(token);
        // 新增文章
        ArticleDO newArticle = new ArticleDO();
        newArticle
                .setAid(UuidUtil.generateStringUuid())
                .setAuthid(tokenDTO.getUserUuid())
                .setTitle(articleVO.getTitle())
                .setTags(gson.toJson(articleVO.getTags()))
                .setDescription(articleVO.getDesc());
        articleMapper.createArticle(newArticle);
    }


    @Override
    public void updateArticle(ArticleVO articleVO, String aid) {
        ArticleDO getArticle = articleMapper.articleExist(aid);
        if (getArticle == null) {
            throw new BusinessException("文章不存在", ErrorCode.EXISTED);
        }
        getArticle
                .setTitle(articleVO.getTitle())
                .setTags(gson.toJson(articleVO.getTags()))
                .setDescription(articleVO.getDesc())
                .setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        articleMapper.updateArticle(getArticle);
    }

    @Override
    public void deleteArticle(String aid) {
        ArticleDO getArticle = articleMapper.getArticleByAid(aid);
        if (getArticle == null) {
            throw new BusinessException("文章不存在", ErrorCode.EXISTED);
        }
        articleMapper.deleteArticle(aid);
    }
}
