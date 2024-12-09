package com.nana.personalblogsystem.model.vo;


import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class AuthChangePasswordVO {
    @NotBlank(message = "旧密码不能为空")
    private String oldPassword;
    @NotBlank(message = "新密码不能为空")
    private String newPassword;
}

