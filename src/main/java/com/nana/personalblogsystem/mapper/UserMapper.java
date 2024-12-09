package com.nana.personalblogsystem.mapper;

import com.nana.personalblogsystem.model.entity.ArticleDO;
import com.nana.personalblogsystem.model.entity.UserDO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @author nana
 * @version v1.0.0
 * @since v1.0.0
 */


@Mapper
public interface UserMapper {
    @Select("SELECT * FROM na_user WHERE email = #{email}")
    UserDO getUserByEmail(String email);

    @Select("SELECT * FROM na_user WHERE uuid = #{uuid}")
    UserDO getUserByUuid(String uuid);

    @Select("SELECT * FROM user WHERE uuid = #{uuid}")
    UserDO getUserByToken(String uuid);

    @Select("SELECT * FROM na_user WHERE username = #{username}")
    UserDO getUserByUsername(String username);

    @Select("SELECT * FROM na_user WHERE username = #{username} OR email = #{email}")
    UserDO selectUser(String username, String email);

    @Insert("INSERT INTO na_user (uuid, username, email, password) VALUES (#{uuid}, #{username}, #{email}, #{password})")
    void createUser(UserDO userDO);

    @Update("UPDATE na_user  SET password = #{password} WHERE uuid = #{uuid}")
    void changePassword(String uuid, String password);
}
