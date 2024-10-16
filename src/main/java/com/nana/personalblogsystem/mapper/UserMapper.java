package com.nana.personalblogsystem.mapper;

import com.nana.personalblogsystem.model.entity.UserDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<UserDO> {
}