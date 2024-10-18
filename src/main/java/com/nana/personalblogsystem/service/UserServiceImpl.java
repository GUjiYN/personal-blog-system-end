package com.nana.personalblogsystem.service;

import com.nana.personalblogsystem.mapper.UserMapper;
import com.nana.personalblogsystem.model.entity.UserDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 29464
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    @Autowired  // 构造器注入，推荐的方式
    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public UserDO login(String usernameOrEmail, String password) {
        return userMapper.getUserByUsernameOrEmail(usernameOrEmail, password);
    }
}
