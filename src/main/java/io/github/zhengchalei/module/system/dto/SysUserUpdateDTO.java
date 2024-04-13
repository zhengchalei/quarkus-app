package io.github.zhengchalei.module.system.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.util.List;

public class SysUserUpdateDTO {

    @NotNull(message = "id不能为空")
    @Schema(description = "id")
    public Long id;

    @NotBlank(message = "用户名不能为空")
    @Schema(description = "用户名")
    public String username;

    @Email
    @NotBlank(message = "用户邮箱不能为空")
    @Schema(description = "用户邮箱")
    public String email;

    @NotNull(message = "部门不能为空")
    @Schema(description = "部门")
    public Long departmentId;

    // 角色
    @NotNull(message = "角色不能为空")
    @Schema(description = "角色")
    public List<Long> roleIds;
}
