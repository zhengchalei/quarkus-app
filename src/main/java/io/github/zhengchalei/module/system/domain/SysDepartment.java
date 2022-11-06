package io.github.zhengchalei.module.system.domain;

import io.github.zhengchalei.common.model.BaseEntity;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.persistence.*;
import javax.ws.rs.QueryParam;
import java.util.List;
import java.util.Objects;

@Schema(title = "系统部门")
@Entity
@Table(name = "sys_department", indexes = {
    @Index(name = "idx_sys_department_name", columnList = "name")
}, uniqueConstraints = {
    @UniqueConstraint(name = "uc_sys_department_name", columnNames = {"name"})
})
@Cacheable
public class SysDepartment extends BaseEntity {

    @QueryParam("name")
    @Schema(title = "部门名称", defaultValue = "总部")
    public String name;

    @QueryParam("description")
    @Schema(title = "部门描述", defaultValue = "官网: https://zhengchalei.github.io")
    public String description;

    @Schema(title = "父级id")
    @Column(name = "parent_id")
    public Long parentId;

    @Schema(title = "排序", defaultValue = "0", example = "0")
    public Integer sort = 0;

    public static List<SysDepartment> findRoots() {
        return SysDepartment.find("parentId is null").list();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SysDepartment that = (SysDepartment) o;
        return Objects.equals(name, that.name) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description);
    }
}
