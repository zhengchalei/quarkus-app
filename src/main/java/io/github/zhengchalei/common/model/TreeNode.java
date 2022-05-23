package io.github.zhengchalei.common.model;

import java.util.List;

/**
 * @author <a href="mailto:stone981023@gmail.com">zhengchalei</a>
 **/
public interface TreeNode<T> {

    Long getId();

    Long getParentId();

    List<T> getChildren();

}
