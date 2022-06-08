package io.github.zhengchalei.module.system.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author <a href="mailto:stone981023@gmail.com">zhengchalei</a>
 * @since 1.0.0
 **/
@Data
public class SysPermissionDto implements Serializable {
    private Long id;
    private Long version;
    private Long parentId;
    private Integer sort = 0;
    private String name;
    private String code;
    private String description;

}
