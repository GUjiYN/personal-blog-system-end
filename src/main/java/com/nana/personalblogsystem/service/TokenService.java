package com.nana.personalblogsystem.service;

import com.nana.personalblogsystem.model.entity.TokenDO;

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

    /**
     * 生成Token
     * <p>
     * 用于生成Token。
     *
     * @param userUuid 用户UUID
     * @return Token
     */
    String generateToken(String userUuid);

    /**
     * 验证Token
     * <p>
     * 用于验证Token。
     *
     * @param token Token
     * @return TokenDO
     */
    TokenDO verifyToken(String token);

    /**
     * 删除Token
     * <p>
     * 用于删除Token。
     *
     * @param token Token
     */
    void deleteToken(String token);
}
