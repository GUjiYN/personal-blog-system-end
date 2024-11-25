package com.nana.personalblogsystem.service;

import com.nana.personalblogsystem.model.dto.TokenDTO;
import com.nana.personalblogsystem.model.entity.TokenDO;
import jakarta.servlet.http.HttpServletRequest;

/**
 * Token服务
 * <p>
 * 用于Token操作。
 *
 * @version v1.0.0
 * @since v1.0.0
 * @author nana
 */
public interface TokenService {
/*

    */
/**
     * 验证Token
     * <p>
     * 用于验证Token。
     *
     * @param token Token
     * @return TokenDO
     *//*

    TokenDO verifyToken(String token);
*/


    /**
     * 生成Token
     * <p>
     * 用于生成Token。
     *
     * @param userUuid 用户UUID
     * @return Token
     */
    String generateToken(String userUuid, Long expiredHourTime, HttpServletRequest request);


    /**
     * 查询Token
     * <p>
     *  用于查询token表中的数据
     *
     *
     */
    TokenDTO selectToken(String token);


    /**
     * 删除Token
     * <p>
     * 用于删除Token。
     *
     * @param token Token
     */
    void deleteToken(String token, HttpServletRequest request);

    void deleteTokenByRequest(HttpServletRequest request);

}
