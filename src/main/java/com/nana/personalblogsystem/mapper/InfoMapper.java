package com.nana.personalblogsystem.mapper;

import com.nana.personalblogsystem.model.entity.InfoDO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface InfoMapper {
    @Insert("INSERT INTO na_info (ikey, ivalue) VALUES (#{ikey}, #{ivalue})")
    public void insertInfo(InfoDO infoDO);

    @Select("SELECT * FROM na_info WHERE ikey = #{ikey}")
    public InfoDO selectInfoByKey(String ikey);
}
