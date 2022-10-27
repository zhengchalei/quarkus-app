package io.github.zhengchalei.module.system.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link io.github.zhengchalei.module.system.domain.SysPermission} entity
 */
public class SysPermissionDto implements Serializable {
    public Long id;
    public Long version;
    public Long parentId;
    public Integer sort = 0;
    public String name;
    public String code;
    public String description;
}
