package com.nana.personalblogsystem.service.impl;

import com.nana.personalblogsystem.mapper.TokenMapper;
import com.nana.personalblogsystem.model.dto.TokenDTO;
import com.nana.personalblogsystem.model.entity.TokenDO;
import com.xlf.utility.ErrorCode;
import com.xlf.utility.exception.BusinessException;
import com.xlf.utility.util.UuidUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
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
@Slf4j
@Service
@RequiredArgsConstructor
public class TokenService implements com.nana.personalblogsystem.service.TokenService {

    private final TokenMapper tokenMapper;

    @Override
    public String generateToken(String userUuid, Long expiredHourTime, HttpServletRequest request) {
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
    public TokenDTO selectToken(String token) {
        log.debug("token: {}",  token);
        // 检查token是否已存在
        TokenDO tokenDO = tokenMapper.selectToken(token);
        if (tokenDO == null) {
            throw new BusinessException("token不存在", ErrorCode.NOT_EXIST);
        }
        TokenDTO tokenDTO = new TokenDTO();
        BeanUtils.copyProperties(tokenDO, tokenDTO);

        return tokenDTO;
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

        TokenDO getToken = tokenMapper.selectToken(token);
        if (getToken == null) {
            throw new BusinessException("token不存在", ErrorCode.EXISTED);
        }
        if (getToken.getExpiredAt().after(new Timestamp(System.currentTimeMillis()))) {
            deleteToken(token);
        }
        return new TokenDO();
    }

    @Override
    public void deleteToken(String token) {
        
        tokenMapper.deleteToken(token);
    }
}
