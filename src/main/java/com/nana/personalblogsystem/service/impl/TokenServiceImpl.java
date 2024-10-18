package com.nana.personalblogsystem.service.impl;

import com.nana.personalblogsystem.mapper.TokenMapper;
import com.nana.personalblogsystem.model.entity.TokenDO;
import com.nana.personalblogsystem.service.TokenService;
import com.xlf.utility.util.UuidUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

/**
 * Token服务实现
 * <p>
 * 用于Token相关操作的服务实现。
 *
 * @version v1.0.0
 * @since v1.0.0
 * @author nana
 */
@Service
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {

    private final TokenMapper tokenMapper;

    @Override
    public String generateToken(String userUuid) {
        String newTokenUuid = UuidUtil.generateStringUuid();
        TokenDO newToken = new TokenDO();
        newToken
                .setTokenUuid(newTokenUuid)
                .setUserUuid(userUuid)
                .setExpiredAt(new Timestamp(System.currentTimeMillis() + 86400000));
        tokenMapper.insertToken(newToken);
        return newTokenUuid;
    }

    @Override
    public TokenDO verifyToken(String token) {
        /*
         * TODO[241018001]
         *  1. 数据库根据 Token 提取 TokenDO
         *  2. 检查 TokenDO 是否存在
         *  3. 检查 Token 是否过期
         *      a. 如果过期，删除 Token
         *      b. 如果未过期，返回 TokenDO
         */
        return new TokenDO();
    }

    @Override
    public void deleteToken(String token) {
        tokenMapper.deleteToken(token);
    }
}
