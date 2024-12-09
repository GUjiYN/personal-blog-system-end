package com.nana.personalblogsystem.service.impl;

import com.nana.personalblogsystem.mapper.TokenMapper;
import com.nana.personalblogsystem.mapper.UserMapper;
import com.nana.personalblogsystem.model.dto.UserDTO;
import com.nana.personalblogsystem.model.entity.TokenDO;
import com.nana.personalblogsystem.model.entity.UserDO;
import com.nana.personalblogsystem.service.UserService;
import com.xlf.utility.ErrorCode;
import com.xlf.utility.exception.BusinessException;
import com.xlf.utility.exception.library.UserAuthenticationException;
import com.xlf.utility.util.HeaderUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.UUID;

/**
 * @version v1.0.0
 * @since v1.0.0
 * @author nana
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final TokenMapper tokenMapper;


    @Override
    public UserDTO getUserByUuid(String userUuid) {
        // 检查用户是否已存在
        UserDO userDO = userMapper.getUserByUuid(userUuid);
        if (userDO == null) {
            throw new BusinessException("用户不存在", ErrorCode.NOT_EXIST);
        }
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(userDO, userDTO);

        return userDTO;
    }

    @Override
    public UserDTO getUserByToken(HttpServletRequest request) {
        UUID getUserUuid = HeaderUtil.getAuthorizeUserUuid(request);
        if (getUserUuid == null) {
            throw new UserAuthenticationException(UserAuthenticationException.ErrorType.TOKEN_EXPIRED, request);
        }
        TokenDO getTokenDO = tokenMapper.selectToken(getUserUuid.toString());
        if (getTokenDO == null) {
            throw new UserAuthenticationException(UserAuthenticationException.ErrorType.TOKEN_EXPIRED, request);
        }
        // 检查令牌是否过期
        if (getTokenDO.getExpiredAt().before(new Timestamp(System.currentTimeMillis()))) {
            tokenMapper.deleteToken(getUserUuid.toString());
            throw new UserAuthenticationException(UserAuthenticationException.ErrorType.TOKEN_EXPIRED, request);
        }
        return this.getUserByUuid(getTokenDO.getUserUuid());
    }
}
