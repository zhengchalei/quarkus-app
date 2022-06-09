package io.github.zhengchalei.module.system.dto;

import java.io.Serializable;

/**
 * @author <a href="mailto:stone981023@gmail.com">zhengchalei</a>
 * @since 1.0.0
 **/
public class SysPermissionDto implements Serializable {
    public Long id;
    public Long version;
    public Long parentId;
    public Integer sort = 0;
    public String name;
    public String code;
    public String description;
}
