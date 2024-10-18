package com.nana.personalblogsystem.service;

import com.nana.personalblogsystem.model.entity.UserDO;

public interface UserService {

    UserDO login(String usernameOrEmail, String password);
}
