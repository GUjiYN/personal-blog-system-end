package com.nana.personalblogsystem.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.gson.Gson;
import com.nana.personalblogsystem.mapper.ArticleMapper;
import com.nana.personalblogsystem.model.CustomPage;
import com.nana.personalblogsystem.model.dto.TokenDTO;
import com.nana.personalblogsystem.model.entity.ArticleDO;
import com.nana.personalblogsystem.model.vo.ArticleVO;
import com.nana.personalblogsystem.service.ArticleService;
import com.xlf.utility.ErrorCode;
import com.xlf.utility.exception.BusinessException;
import com.xlf.utility.util.UuidUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

/**
 * 文章逻辑
 * <hr/>
 * 文章逻辑，包含文章相关逻辑
 *
 * @author nana
 * @version v1.0.0
 * @since v1.0.0
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {

    private final ArticleMapper articleMapper;
    private final TokenService tokenService;
    private final Gson gson;

    @Override
    public Page<ArticleDO> getArticleList(Integer page, Integer size) {
        // 计算偏移量 (从第 1 页开始)
        int offset = (page - 1) * size;

        // 获取当前页的文章列表
        List<ArticleDO> records = articleMapper.getArticleList(offset, size);

        // 获取文章的总记录数
        long total = articleMapper.countArticles();

        // 构建分页对象
        Page<ArticleDO> articlePage = new Page<>();
        articlePage.setRecords(records);
        articlePage.setTotal(total);
        articlePage.setSize((long) size);
        articlePage.setCurrent((long) page);
        articlePage.setPages((total + size - 1) / size); // 计算总页数

        return articlePage;
    }


    @Override
    public void createArticle(ArticleVO articleVO, HttpServletRequest request) {
        // 检查文章是否已存在
        ArticleDO getArticle = articleMapper.articleRepeat(articleVO.getTitle(), articleVO.getDescription());
        if (getArticle != null) {
            throw new BusinessException("文章已存在", ErrorCode.EXISTED);
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
                .setDescription(articleVO.getDescription());
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
                .setDescription(articleVO.getDescription())
                .setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        articleMapper.updateArticle(getArticle);
    }

    @Override
    public void deleteArticle(String aid) {
        ArticleDO getArticle = articleMapper.articleExist(aid);
        if (getArticle == null) {
            throw new BusinessException("文章不存在", ErrorCode.EXISTED);
        }
        articleMapper.deleteArticle(aid);
    }
}
