package io.github.zhengchalei.module.system.domain;

import io.github.zhengchalei.common.model.BaseEntity;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Schema(title = "系统用户")
@Entity
@Table(name = "sys_user")
public class SysUser extends BaseEntity {

    @Schema(title = "用户名", example = "super_admin")
    public String username;

    @Email
    @Schema(title = "用户邮箱", example = "stone981023@gmail.com")
    public String email;
    @Schema(title = "用户密码", example = "123456")
    public String password;

    @Schema(title = "用户描述", example = "123456")
    @ManyToOne
    @JoinColumn(name = "department_id")
    public SysDepartment department;

    @Schema(title = "用户角色")
    @ManyToMany(fetch = FetchType.LAZY)
    @Fetch(FetchMode.SUBSELECT)
    @JoinTable(name = "sys_user_role", joinColumns = @JoinColumn(name = "sys_user_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "sys_role_id", referencedColumnName = "id"))
    public Set<SysRole> roles = new LinkedHashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SysUser sysUser = (SysUser) o;
        return Objects.equals(username, sysUser.username) && Objects.equals(email, sysUser.email) && Objects.equals(password, sysUser.password) && Objects.equals(department, sysUser.department) && Objects.equals(roles, sysUser.roles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, email, password, department, roles);
    }
}
