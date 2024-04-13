package io.github.zhengchalei.module.system.dto;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.util.List;

public class SysUserUpdateDTO {
    @Schema(description = "id")
    public Long id;
    @Schema(description = "用户名")
    public String username;
    @Schema(description = "用户邮箱")
    public String email;
    // 角色
    public List<Long> roleIds;
}
