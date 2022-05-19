package io.github.zhengchalei.module.auth.dto;

import lombok.Data;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.validation.constraints.NotBlank;

@Data
@Schema(title = "登录DTO", name = "LoginDto")
public class LoginDTO {

    @Schema(title = "用户名", example = "admin", nullable = false)
    @NotBlank(message = "用户名不能为空")
    String username;

    @Schema(title = "密码", example = "admin", nullable = false)
    @NotBlank(message = "密码不能为空")
    String password;

}
