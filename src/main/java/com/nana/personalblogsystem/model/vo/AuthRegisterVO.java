package com.nana.personalblogsystem.model.vo;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

/**
 * 注册视图对象
 * <p>
 * 用于接收注册请求的数据。
 *
 * @version v1.0.0
 * @since v1.0.0
 * @author nana
 */
@Getter
public class AuthRegisterVO {
    @NotBlank(message = "用户名不能为空")
    @Pattern(regexp = "^[A-Za-z0-9-_]{4,36}$", message = "用户名格式错误")
    private String username;
    @NotBlank(message = "邮箱不能为空")
    @Email(message = "邮箱格式错误")
    private String email;
    @NotBlank(message = "密码不能为空")
    private String password;
}
