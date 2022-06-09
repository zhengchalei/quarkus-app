package io.github.zhengchalei.module.system.dto;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author <a href="mailto:stone981023@gmail.com">zhengchalei</a>
 * @since 1.0.0
 **/
public class SysRoleDto implements Serializable {
    public Long id;
    public Long version;
    public String name;
    public String code;
    public String description;
    public Set<SysPermissionDto> Permissions = new LinkedHashSet<>();
}
