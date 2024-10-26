package com.nana.personalblogsystem.controller;

import com.nana.personalblogsystem.model.dto.UserDTO;
import com.nana.personalblogsystem.service.UserService;
import com.xlf.utility.BaseResponse;
import com.xlf.utility.ResultUtil;
import com.xlf.utility.annotations.HasAuthorize;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户控制器
 * @version v1.0.0
 * @since v1.0.0
 * @author nana
 */

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    /**
     * 获取当前用户
     * <p>
     * 该方法用于获取当前用户; 根据用户uuid获取用户信息;
     *
     * @param userUuid 用户uuid
     * @return 用户信息
     */

    @HasAuthorize
    @GetMapping("/current/{userUuid}")
    public ResponseEntity<BaseResponse<UserDTO>> userCurrent(
            @PathVariable String userUuid
    ) {
        UserDTO getUser = userService.getUserByUuid(userUuid);
        return ResultUtil.success("获取成功", getUser);
    }
}

