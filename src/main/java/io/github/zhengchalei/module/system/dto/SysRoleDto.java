package io.github.zhengchalei.module.system.dto;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * A DTO for the {@link io.github.zhengchalei.module.system.domain.SysRole} entity
 */
public class SysRoleDto implements Serializable {
    public Long id;
    public Long version;
    public String name;
    public String code;
    public String description;
    public Set<SysPermissionDto> Permissions = new LinkedHashSet<>();

}
