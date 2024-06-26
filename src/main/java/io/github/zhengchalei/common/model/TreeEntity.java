package io.github.zhengchalei.common.model;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Transient;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.hibernate.Hibernate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 实现了 TreeNode 的 节点实现类
 *
 * @author <a href="mailto:stone981023@gmail.com">zhengchalei</a>
 **/
@MappedSuperclass
public abstract class TreeEntity<T> extends BaseEntity implements TreeNode<T> {

    /**
     * 父id
     */
    @Schema(title = "父级id")
    @Column(name = "parent_id")
    public Long parentId;

    /**
     * 排序
     */
    @Schema(title = "排序", defaultValue = "0", example = "0")
    public Integer sort = 0;

    /**
     * 子节点
     */
    @Schema(hidden = true)
    @Transient
    public List<T> children = new ArrayList<>();

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    @Override
    public Long getParentId() {
        return this.parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    @Override
    public List<T> getChildren() {
        return this.children;
    }

    public void setChildren(List<T> children) {
        this.children = children;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        TreeEntity<?> that = (TreeEntity<?>) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
