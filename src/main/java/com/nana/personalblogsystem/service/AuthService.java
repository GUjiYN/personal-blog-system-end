package com.nana.personalblogsystem.service;

import com.nana.personalblogsystem.model.entity.UserDO;
import com.nana.personalblogsystem.model.vo.AuthRegisterVO;
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
     * 注册用户
     * <p>
     * 该方法用于注册用户；注册用户时，需要提供 {@code 用户名}、{@code 邮箱}、{@code 密码}；
     * 注册成功后，返回用户信息。
     *
     * @param authRegisterVO 授权注册值对象
     * @return 用户UUID
     */
    String register(AuthRegisterVO authRegisterVO);

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

    void checkUserAndPassword(String userUuid, String password, HttpServletRequest request);

    void changePassword(String uuid, String password);

}
