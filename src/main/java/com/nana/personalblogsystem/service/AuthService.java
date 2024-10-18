package com.nana.personalblogsystem.service;

import com.nana.personalblogsystem.model.entity.UserDO;
import jakarta.servlet.http.HttpServletRequest;

/**
 * 认证服务
 * <p>
 * 用于用户认证。
 *
 * @version v1.0.0
 * @since v1.0.0
 * @author nana
 */
public interface AuthService {

    /**
     * 注册
     * <p>
     * 用于用户注册。
     *
     * @param username 用户名
     * @param email 邮箱
     * @param password 密码
     */
    void register(String username, String email, String password);

    /**
     * 登录
     * <p>
     * 用于用户登录。
     *
     * @param user 用户名或邮箱
     * @param password 密码
     * @return 用户信息
     */
    UserDO login(String user, String password, HttpServletRequest request);

}
