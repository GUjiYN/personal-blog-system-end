package com.nana.personalblogsystem.service.impl;

import com.nana.personalblogsystem.mapper.UserMapper;
import com.nana.personalblogsystem.model.dto.UserDTO;
import com.nana.personalblogsystem.model.entity.UserDO;
import com.nana.personalblogsystem.service.UserService;
import com.xlf.utility.ErrorCode;
import com.xlf.utility.exception.BusinessException;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * @version v1.0.0
 * @since v1.0.0
 * @author nana
 */

@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

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

}
