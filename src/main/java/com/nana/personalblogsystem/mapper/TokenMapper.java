package com.nana.personalblogsystem.mapper;

import com.nana.personalblogsystem.model.entity.TokenDO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * Token映射器
 * <p>
 * 用于Token数据的持久化。
 *
 * @version v1.0.0
 * @since v1.0.0
 * @author nana
 */
@Mapper
public interface TokenMapper {
    @Select("SELECT * from na_token WHERE token_uuid = #{uuid}")
    TokenDO selectToken(String uuid);

    @Delete("DELETE FROM na_token WHERE token_uuid = #{uuid}")
    void deleteToken(String uuid);

    @Insert("INSERT INTO na_token (token_uuid, user_uuid, expired_at) VALUES (#{tokenUuid}, #{userUuid}, #{expiredAt})")
    void insertToken(TokenDO token);
}
