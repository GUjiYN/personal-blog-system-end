package com.nana.personalblogsystem.service.impl;

import com.nana.personalblogsystem.mapper.TagMapper;
import com.nana.personalblogsystem.model.CustomPage;
import com.nana.personalblogsystem.model.dto.ArticleDTO;
import com.nana.personalblogsystem.model.dto.TagDTO;
import com.nana.personalblogsystem.model.entity.ArticleDO;
import com.nana.personalblogsystem.model.entity.TagDO;
import com.nana.personalblogsystem.model.vo.TagVO;
import com.nana.personalblogsystem.service.TagService;
import com.nana.personalblogsystem.util.CopyUtil;
import com.xlf.utility.ErrorCode;
import com.xlf.utility.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 标签逻辑
 * <hr/>
 * 标签逻辑，包含标签相关逻辑
 *
 * @author nana
 * @version v1.0.0
 * @since v1.0.0
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {
    private final TagMapper tagMapper;
    @Override
    public CustomPage<TagDTO> getAllTags(Integer page, Integer size){
        List<TagDO> allTags = tagMapper.getAllTags();
        CustomPage<TagDTO> customPage = new CustomPage<>();
        CopyUtil.pageDoCopyToDTO(allTags, customPage,Long.valueOf(page), Long.valueOf(size), TagDTO.class);
         return customPage;
    }

    @Override
    public void deleteTag(String tname) {
        TagDO getTag = tagMapper.tagExist(tname);
        if (getTag == null) {
            throw new BusinessException("标签不存在", ErrorCode.EXISTED);
        }
        tagMapper.deleteTag(tname);
    }

}
