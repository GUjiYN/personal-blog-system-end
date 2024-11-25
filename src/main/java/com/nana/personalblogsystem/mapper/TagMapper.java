package com.nana.personalblogsystem.mapper;

import com.nana.personalblogsystem.model.CustomPage;
import com.nana.personalblogsystem.model.dto.ArticleDTO;
import com.nana.personalblogsystem.model.dto.TagDTO;
import com.nana.personalblogsystem.model.entity.TagDO;
import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface TagMapper {
    @Select("SELECT * FROM na_tag ORDER BY tid DESC")
   List<TagDO> getAllTags();

    @Insert("INSERT INTO na_tag (tname) VALUES (#{tname})")
    void insertTag(TagDO tagDO);


    @Select("SELECT * FROM  na_tag WHERE tname = #{tname}")
    public TagDO tagExist(String tname);

    @Delete("DELETE FROM na_tag WHERE tname = #{tname}")
    void deleteTag(String tname);
}
