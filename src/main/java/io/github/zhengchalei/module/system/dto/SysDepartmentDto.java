package io.github.zhengchalei.module.system.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link io.github.zhengchalei.module.system.domain.SysDepartment} entity
 */
public class SysDepartmentDto implements Serializable {
    public Long id;
    public Long version;
    public Long parentId;
    public Integer sort = 0;
    public String name;
    public String description;

}
