package io.github.zhengchalei.module.system.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

public class SysUserSaveDTO {
    @NotBlank(message = "用户名不能为空")
    @Schema(description = "用户名")
    public String username;

    @Email
    @NotBlank(message = "用户邮箱不能为空")
    @Schema(description = "用户邮箱")
    public String email;
}
