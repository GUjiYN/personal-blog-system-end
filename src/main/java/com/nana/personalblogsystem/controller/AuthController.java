package com.nana.personalblogsystem.controller;

import com.nana.personalblogsystem.model.dto.AuthUserDTO;
import com.nana.personalblogsystem.model.dto.UserDTO;
import com.nana.personalblogsystem.model.entity.UserDO;
import com.nana.personalblogsystem.model.vo.AuthRegisterVO;
import com.nana.personalblogsystem.service.AuthService;
import com.nana.personalblogsystem.service.TokenService;
import com.xlf.utility.BaseResponse;
import com.xlf.utility.ResultUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
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
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final TokenService tokenService;

    /**
     * 注册
     * <p>
     * 用于用户注册。
     *
     * @param authRegisterVO 注册视图对象
     * @return 响应
     */
    @PostMapping("/register")
    public ResponseEntity<BaseResponse<Void>> register(@RequestBody @Validated AuthRegisterVO authRegisterVO) {
        authService.register(authRegisterVO.getUsername(), authRegisterVO.getEmail(), authRegisterVO.getPassword());
        return ResultUtil.success("注册成功");
    }

    /**
     * 登录
     * <p>
     * 用于用户登录。
     *
     * @param user 用户名或邮箱
     * @param password        密码
     * @param request         请求
     * @return 响应
     */
    @PostMapping("/login")
    public ResponseEntity<BaseResponse<AuthUserDTO>> login(
            @RequestParam String user,
            @RequestParam String password,
            HttpServletRequest request
    ) {
        UserDO getUser = authService.login(user, password, request);
        String getToken = tokenService.generateToken(getUser.getUuid());
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(getUser, userDTO);
        AuthUserDTO authUserDTO = new AuthUserDTO();
        authUserDTO
                .setUser(userDTO)
                .setToken(getToken);
        return ResultUtil.success("登录成功", authUserDTO);
    }
}
