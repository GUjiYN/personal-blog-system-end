package com.nana.personalblogsystem.controller;

import com.nana.personalblogsystem.model.dto.AuthUserDTO;
import com.nana.personalblogsystem.model.dto.UserDTO;
import com.nana.personalblogsystem.model.entity.UserDO;
import com.nana.personalblogsystem.model.vo.AuthChangePasswordVO;
import com.nana.personalblogsystem.model.vo.AuthLoginVO;
import com.nana.personalblogsystem.model.vo.AuthRegisterVO;
import com.nana.personalblogsystem.service.AuthService;
import com.nana.personalblogsystem.service.InfoService;
import com.nana.personalblogsystem.service.TokenService;
import com.nana.personalblogsystem.service.UserService;
import com.nana.personalblogsystem.service.impl.UserServiceImpl;
import com.xlf.utility.BaseResponse;
import com.xlf.utility.ResultUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 授权控制器
 * <p>
 * 用作用户授权相关操作。
 *
 * @author nana
 * @version v1.0.0
 * @since v1.0.0
 */
@CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final TokenService tokenService;
    private final UserService userService;
    private final InfoService infoService;
    /**
     * 注册
     * <p>
     * 用于用户注册。
     *
     * @param authRegisterVO 注册视图对象
     * @return 响应
     */

    @PostMapping("/register")
    @Transactional
    public  ResponseEntity<BaseResponse<AuthUserDTO>>  register(
            @RequestBody @Validated AuthRegisterVO authRegisterVO,
            HttpServletRequest request
    ) {
        String getUserUuid = authService.register(authRegisterVO);
        UserDTO getUserDTO = userService.getUserByUuid(getUserUuid);
        String getToken = tokenService.generateToken(getUserUuid, 12L, request);
        AuthUserDTO authUserDTO = new AuthUserDTO()
                .setUser(getUserDTO)
                .setToken(getToken);
        return ResultUtil.success("注册成功",authUserDTO);
    }

    /**
     * 登录
     * <p>
     * 用于用户登录。
     *
     * @param authLoginVO  登录视图对象
     * @param request         请求
     * @return 响应
     */
    @PostMapping("/login")
    public ResponseEntity<BaseResponse<AuthUserDTO>> login(
            @RequestBody @Validated AuthLoginVO authLoginVO,
            HttpServletRequest request
    ) {
        UserDO getUser = authService.login(authLoginVO.getUser(), authLoginVO.getPassword(), request);

        // 判断是否为超级管理员
        String superAdminUuid = infoService.getSuperAdminUuid();
        String role = getUser.getUuid().equals(superAdminUuid) ? "super_admin" : "user";
        getUser.setRole(role);

        String getToken = tokenService.generateToken(getUser.getUuid(), 12L, request);
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(getUser, userDTO);
        AuthUserDTO authUserDTO = new AuthUserDTO();
        authUserDTO
                .setUser(userDTO)
                .setToken(getToken);
        return ResultUtil.success("登录成功", authUserDTO);
    }

    /**
     * 修改密码
     * <p>
     * 该方法用于修改密码。
     *
     * @return {@link Void} 空
     */
    @PutMapping("/change/password")
    public ResponseEntity<BaseResponse<Void>> changePassword(
            @Validated @RequestBody AuthChangePasswordVO authChangePasswordVO,
            HttpServletRequest request
    ) {
        UserDTO getUserDTO = userService.getUserByToken(request);
        authService.checkUserAndPassword(getUserDTO.getUuid(), authChangePasswordVO.getOldPassword(), request);
        authService.changePassword(getUserDTO.getUuid(), authChangePasswordVO.getNewPassword());
        return ResultUtil.success("修改密码成功");
    }


    /**
     * 登出
     * <p>
     * 该方法用于登出。
     *
     * @return {@link Void} 空
     */
    @GetMapping("/logout")
    public ResponseEntity<BaseResponse<Void>> logout(HttpServletRequest request) {
        tokenService.deleteTokenByRequest(request);
        return ResultUtil.success("登出成功");
    }



}
