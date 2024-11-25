package com.nana.personalblogsystem.service;

import com.nana.personalblogsystem.model.CustomPage;
import com.nana.personalblogsystem.model.dto.TagDTO;
import com.nana.personalblogsystem.model.vo.TagVO;

public interface TagService {
    CustomPage<TagDTO> getAllTags(Integer page, Integer size);

    void deleteTag(String tname);
}
