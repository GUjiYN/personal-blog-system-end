package com.nana.personalblogsystem.service.impl;

import com.nana.personalblogsystem.mapper.InfoMapper;
import com.nana.personalblogsystem.mapper.UserMapper;
import com.nana.personalblogsystem.model.entity.InfoDO;
import com.nana.personalblogsystem.model.entity.UserDO;
import com.nana.personalblogsystem.model.vo.AuthRegisterVO;
import com.nana.personalblogsystem.service.AuthService;
import com.xlf.utility.ErrorCode;
import com.xlf.utility.exception.BusinessException;
import com.xlf.utility.exception.library.UserAuthenticationException;
import com.xlf.utility.util.PasswordUtil;
import com.xlf.utility.util.UuidUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

/**
 * 认证服务实现
 * <p>
 * 用于用户认证。
 *
 * @author nana
 * @version v1.0.0
 * @since v1.0.0
 */
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserMapper userMapper;
    private final InfoMapper infoMapper;


    @Override
    public String register(@NotNull AuthRegisterVO authRegisterVO) {
        // 检查用户是否已存在
        UserDO getUser = userMapper.userExist(authRegisterVO.getUsername(), authRegisterVO.getPassword());
        if (getUser != null) {
            throw new BusinessException("用户已存在", ErrorCode.EXISTED);
        }
        // 注册用户
        String newUserUuid = UuidUtil.generateStringUuid();
        InfoDO newInfo = new InfoDO();
        newInfo
                .setIkey(newUserUuid)
                .setIvalue(authRegisterVO.getUsername());
        infoMapper.insertInfo(newInfo);
        UserDO newUser = new UserDO();
        newUser
                .setUuid(newUserUuid)
                .setUsername(authRegisterVO.getUsername())
                .setEmail(authRegisterVO.getEmail())
                .setPassword(PasswordUtil.encrypt(authRegisterVO.getPassword()));
        userMapper.createUser(newUser);
        return newUserUuid;
    }

    @Override
    public UserDO login(String user, String password, HttpServletRequest request) {
        UserDO getUser;
        if (Pattern.matches("^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$", user)) {
            getUser = userMapper.getUserByEmail(user);
        } else {
            getUser = userMapper.getUserByUsername(user);
        }
        if (getUser == null) {
            throw new BusinessException("用户不存在", ErrorCode.NOT_EXIST);
        }
        // 判断用户是否登录成功
        if (!PasswordUtil.verify(password, getUser.getPassword())) {
            throw new UserAuthenticationException(UserAuthenticationException.ErrorType.WRONG_PASSWORD, request);
        }
        return getUser;
    }
}
