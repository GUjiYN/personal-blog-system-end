package com.nana.personalblogsystem.service.impl;

import com.nana.personalblogsystem.mapper.ArticleMapper;
import com.nana.personalblogsystem.model.entity.ArticleDO;
import com.nana.personalblogsystem.model.vo.ArticleVO;
import com.nana.personalblogsystem.service.ArticleService;
import com.xlf.utility.ErrorCode;
import com.xlf.utility.exception.BusinessException;
import com.xlf.utility.util.UuidUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * 文章逻辑
 * <hr/>
 * 文章逻辑，包含文章相关逻辑
 *
 * @author nana
 *  * @version v1.0.0
 *  * @since v1.0.0
 */
@Service
public class ArticleServiceImpl implements ArticleService {

   private final ArticleMapper articleMapper;

   public ArticleServiceImpl(ArticleMapper articleMapper) {
       this.articleMapper = articleMapper;
   }

    @Override
    public void createArticle(String title, String desc, String tags) {
        // 检查文章是否已存在
        ArticleDO getArticle = articleMapper.articleExist(title, desc);
        if (getArticle != null) {
            throw new BusinessException("文章名已存在", ErrorCode.EXISTED);
        }
        // 新增文章
        ArticleDO newArticle = new ArticleDO();
        newArticle
                .setAid(UuidUtil.generateStringUuid())
                .setTitle(title)
                .setTags(tags)
                .setDesc(desc);
        articleMapper.createArticle(newArticle);
    }


    @Override
    public void updateArticle(ArticleVO article, String aid) {
       ArticleDO getArticle = articleMapper.articleExist(article.getTitle(), article.getDesc());
       if (getArticle == null) {
           throw new BusinessException("文章不存在", ErrorCode.EXISTED);
       }
        BeanUtils.copyProperties(article, getArticle);
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
