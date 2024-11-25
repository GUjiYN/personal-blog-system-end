package com.nana.personalblogsystem.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.gson.Gson;
import com.nana.personalblogsystem.mapper.ArticleMapper;
import com.nana.personalblogsystem.mapper.TagMapper;
import com.nana.personalblogsystem.model.CustomPage;
import com.nana.personalblogsystem.model.dto.ArticleDTO;
import com.nana.personalblogsystem.model.dto.TokenDTO;
import com.nana.personalblogsystem.model.entity.ArticleDO;
import com.nana.personalblogsystem.model.entity.TagDO;
import com.nana.personalblogsystem.model.entity.TokenDO;
import com.nana.personalblogsystem.model.vo.ArticleVO;
import com.nana.personalblogsystem.model.vo.TagVO;
import com.nana.personalblogsystem.service.ArticleService;
import com.nana.personalblogsystem.service.TagService;
import com.nana.personalblogsystem.util.CopyUtil;
import com.nana.personalblogsystem.util.PaginationUtil;
import com.xlf.utility.ErrorCode;
import com.xlf.utility.exception.BusinessException;
import com.xlf.utility.util.UuidUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.relational.core.sql.In;
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
    private final TagMapper tagMapper;
    private final TagServiceImpl tagService;
    private final TokenServiceImpl tokenService;
    private final Gson gson;

    @Override
    public CustomPage<ArticleDTO> getArticleList(Integer page, Integer size) {
        // 获取所有文章数据
        List<ArticleDO> allArticles = articleMapper.getAllArticles();
        CustomPage<ArticleDTO> customPage = new CustomPage<>();
        CopyUtil.pageDoCopyToDTO(allArticles, customPage, Long.valueOf(page), Long.valueOf(size), ArticleDTO.class);

        // 使用工具类进行分页处理
        return customPage;
    }

    @Override
    public CustomPage<ArticleDTO> getArticlesByTitleOrDesc(String keyword, Integer page, Integer size) {
        List<ArticleDO> getArticlesByTitleOrDesc = articleMapper.getArticlesByTitleOrDesc(keyword);
        if (getArticlesByTitleOrDesc == null) {
            throw new BusinessException("文章不存在", ErrorCode.NOT_EXIST);
        }
        CustomPage<ArticleDTO> customPage = new CustomPage<>();
        CopyUtil.pageDoCopyToDTO(getArticlesByTitleOrDesc, customPage, Long.valueOf(page), Long.valueOf(size), ArticleDTO.class);
        return customPage;
    }

    @Override
    public CustomPage<ArticleDTO> getArticlesByTags(String tags, Integer page, Integer size) {
        List<ArticleDO> getArticlesByTags = articleMapper.getArticlesByTags(tags);
        if (getArticlesByTags == null) {
            throw new BusinessException("文章不存在", ErrorCode.NOT_EXIST);
        }
        CustomPage<ArticleDTO> customPage = new CustomPage<>();
        CopyUtil.pageDoCopyToDTO(getArticlesByTags, customPage, Long.valueOf(page), Long.valueOf(size), ArticleDTO.class);
        return customPage;
    }

    @Override
    public ArticleDTO getArticleDetails(String aid){
        ArticleDO articleDO = articleMapper.getArticleDetails(aid);
        if (articleDO == null) {
            throw new BusinessException("文章不存在", ErrorCode.NOT_EXIST);
        }
        return gson.fromJson(gson.toJson(articleDO), ArticleDTO.class);
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
                .setDescription(articleVO.getDescription());
        // 处理标签
        String tagsJson = gson.toJson(articleVO.getTags());
        newArticle.setTags(tagsJson);


        // 处理标签：遍历文章的标签列表，逐个检查并插入不存在的标签
        articleVO.getTags().forEach(tags -> {
            TagDO newTag = new TagDO();
            newTag.setTname(tags);
            TagDO getTag = tagMapper.tagExist(tags);
            if (getTag == null) {
                tagMapper.insertTag(newTag);
            }
        });
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
