package io.github.zhengchalei.module.system.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author <a href="mailto:stone981023@gmail.com">zhengchalei</a>
 * @since 1.0.0
 **/
@Data
public class SysRoleDto implements Serializable {
    private Long id;
    private Long version;
    private String name;
    private String code;
    private String description;
    private Set<SysPermissionDto> Permissions = new LinkedHashSet<>();

}
