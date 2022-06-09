package io.github.zhengchalei.common.model;

import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
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

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public void setChildren(List<T> children) {
        this.children = children;
    }

    @Override
    public Long getParentId() {
        return this.parentId;
    }

    @Override
    public List<T> getChildren() {
        return this.children;
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
