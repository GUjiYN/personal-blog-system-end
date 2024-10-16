package com.nana.personalblogsystem.dao;

import com.nana.personalblogsystem.mapper.UserMapper;
import com.nana.personalblogsystem.model.entity.UserDO;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAO extends ServiceImpl<UserMapper, UserDO> implements IService<UserDO> {
}
