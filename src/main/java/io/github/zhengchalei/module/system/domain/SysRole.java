package io.github.zhengchalei.module.system.domain;

import io.github.zhengchalei.common.model.BaseEntity;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.ws.rs.QueryParam;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Schema(title = "系统角色")
@Entity(name = "sys_role")
@Table(name = "sys_role", uniqueConstraints = {
        @UniqueConstraint(name = "uc_sys_role_name_code", columnNames = {"name", "code"})
})
public class SysRole extends BaseEntity {

    @QueryParam("name")
    @Schema(title = "角色名称", example = "超级管理员")
    public String name;

    @QueryParam("code")
    @Schema(title = "角色编码", example = "super_admin")
    public String code;

    @Schema(title = "角色描述", example = "超级管理员, 拥有所有权限")
    public String description;

    @Schema(title = "角色权限")
    // 授权: Authorization, 权限: Permission, 权力
    @ManyToMany(fetch = FetchType.LAZY)
    @Fetch(FetchMode.SUBSELECT)
    @JoinTable(name = "sys_role_permission",
            joinColumns = @JoinColumn(name = "sys_role_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "sys_permission_id", referencedColumnName = "id")
    )
    public Set<SysPermission> permissions = new LinkedHashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SysRole sysRole = (SysRole) o;
        return Objects.equals(name, sysRole.name) && Objects.equals(code, sysRole.code) && Objects.equals(description, sysRole.description) && Objects.equals(permissions, sysRole.permissions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, code, description, permissions);
    }
}
