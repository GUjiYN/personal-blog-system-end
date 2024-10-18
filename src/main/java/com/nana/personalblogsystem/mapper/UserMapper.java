package com.nana.personalblogsystem.mapper;

import com.nana.personalblogsystem.model.entity.UserDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author 29464
 */
@Mapper
public interface UserMapper {
    @Select("select * FROM na_user WHERE (username = #{usernameOrEmail} OR email = #{usernameOrEmail}) AND password = #{password}")
    UserDO getUserByUsernameOrEmail(@Param("usernameOrEmail") String usernameOrEmail, @Param("password") String password);
}