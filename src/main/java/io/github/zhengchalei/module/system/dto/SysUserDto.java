package io.github.zhengchalei.module.system.dto;

import io.github.zhengchalei.module.system.domain.SysUser;

import io.quarkus.hibernate.orm.panache.common.ProjectedFieldName;
import jakarta.validation.constraints.Email;
import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * A DTO for the {@link SysUser} entity
 */
public class SysUserDto implements Serializable {
    public Long id;
    public Long version;
    public String username;
    public String email;
    public String password;
    public SysDepartmentDto department;
    public Set<SysRoleDto> roles = new LinkedHashSet<>();
}
