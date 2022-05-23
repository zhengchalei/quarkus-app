package io.github.zhengchalei.common.model;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:stone981023@gmail.com">zhengchalei</a>
 **/
@MappedSuperclass
public class TreeEntity<T> extends BaseEntity implements TreeNode<T> {

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

    @Override
    public Long getParentId() {
        return this.parentId;
    }

    @Override
    public List<T> getChildren() {
        return this.children;
    }
}
