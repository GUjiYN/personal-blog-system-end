package com.nana.personalblogsystem.mapper;

import com.nana.personalblogsystem.model.entity.UserDO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author 29464
 */
@Mapper
public interface UserMapper {
    @Select("SELECT * FROM na_user WHERE email = #{email}")
    UserDO getUserByEmail(String email);

    @Select("SELECT * FROM na_user WHERE username = #{username}")
    UserDO getUserByUsername(String username);

    @Select("SELECT * FROM na_user WHERE username = #{username} OR email = #{email}")
    UserDO userExist(String username, String email);

    @Insert("INSERT INTO na_user (uuid, username, email, password) VALUES (#{uuid}, #{username}, #{email}, #{password})")
    void createUser(UserDO userDO);
}
