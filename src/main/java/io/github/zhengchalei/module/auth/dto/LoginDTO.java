package io.github.zhengchalei.module.auth.dto;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import jakarta.validation.constraints.NotBlank;

@Schema(title = "登录DTO", name = "LoginDto")
public class LoginDTO {

    @Schema(title = "用户名", example = "admin", nullable = false)
    @NotBlank(message = "用户名不能为空")
    public String username;

    @Schema(title = "密码", example = "123456", nullable = false)
    @NotBlank(message = "密码不能为空")
    public String password;

}
