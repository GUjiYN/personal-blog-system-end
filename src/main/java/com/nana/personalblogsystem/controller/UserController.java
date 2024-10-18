package com.nana.personalblogsystem.controller;

import com.nana.personalblogsystem.model.entity.UserDO;
import com.nana.personalblogsystem.service.UserServiceImpl;
import com.xlf.utility.BaseResponse;
import com.xlf.utility.ErrorCode;
import com.xlf.utility.ResultUtil;
import com.xlf.utility.exception.BusinessException;
import com.xlf.utility.exception.library.UserAuthenticationException;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * 用户登录
 *
 * @author 29464
 */
@RestController
@RequestMapping("/api")
public class UserController {

    private final UserServiceImpl userService;

    @Autowired
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<BaseResponse<Void>> login(@RequestParam String usernameOrEmail, @RequestParam String password, HttpServletRequest request) {
        UserDO user = userService.login(usernameOrEmail, password);
        if (user != null) {
            return ResultUtil.success("登录成功！");
        } else {
            throw new UserAuthenticationException(UserAuthenticationException.ErrorType.WRONG_PASSWORD, request);
        }
    }
}

