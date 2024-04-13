package io.github.zhengchalei.module.system.domain;

import io.github.zhengchalei.common.model.BaseEntity;
import jakarta.persistence.*;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.util.Objects;

@Cacheable
@Schema(title = "系统权限")
@Entity(name = "sys_permission")
@Table(name = "sys_permission", uniqueConstraints = {
        @UniqueConstraint(name = "uc_sys_permission_name_code", columnNames = {"name", "code"})
})
public class SysPermission extends BaseEntity {

    @Schema(title = "权限名称", defaultValue = "系统管理")
    public String name;

    @Schema(title = "权限编码", defaultValue = "system")
    public String code;

    @Schema(title = "权限描述", defaultValue = "系统管理权限")
    public String description;

    @Schema(title = "父级id")
    @Column(name = "parent_id")
    public Long parentId;

    @Schema(title = "排序", defaultValue = "0", example = "0")
    public Integer sort = 0;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SysPermission that = (SysPermission) o;
        return Objects.equals(name, that.name) && Objects.equals(code, that.code) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, code, description);
    }
}
