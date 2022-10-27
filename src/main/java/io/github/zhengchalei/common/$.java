package io.github.zhengchalei.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.zhengchalei.common.exception.ServiceException;
import io.github.zhengchalei.common.model.TreeEntity;
import io.github.zhengchalei.common.model.TreeNode;

import java.util.Collection;
import java.util.List;

/**
 * 工具类
 *
 * @author <a href="mailto:stone981023@gmail.com">zhengchalei</a>
 **/
public class $ {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 生成子节点
     *
     * @param root 根节点
     * @param list 数据
     * @return {@code T}
     */
    private static <T extends TreeNode<T>> T genChildren(final T root, final Collection<T> list) {
        list
            .stream()
            .filter(it -> root.getId().equals(it.getParentId()))
            .forEach(it -> root.getChildren().add(genChildren(it, list)));
        return root;
    }

    /**
     * 多节点树
     *
     * @param list 列表
     * @return {@link TreeEntity}<{@link T}>
     */
    public static <T extends TreeNode<T>> List<T> tree(List<T> list) {
        // 找到 Root
        return list.stream()
            .filter(t -> t.getParentId() == null)
            .map(it -> genChildren(it, list))
            .toList();
    }

    /**
     * 单节点树
     *
     * @param list 列表
     * @return {@link TreeEntity}<{@link T}>
     */
    public static <T extends TreeNode<T>> T rootTree(List<T> list) {
        final long count = list.stream().filter(treeNode -> treeNode.getParentId() == null).count();
        if (count > 1) {
            // 数据异常, 具有多个根节点
            throw new RuntimeException("数据异常, 具有多个根节点");
        }
        return tree(list).iterator().next();
    }


    public static String firstLowerCase(String str) {
        // 进行字母的ascii编码前移，效率要高于截取字符串进行转换的操作
        char[] cs = str.toCharArray();
        cs[0] += 32;
        return String.valueOf(cs);
    }

    public static String firstToUpperCase(String str) {
        // 进行字母的ascii编码前移，效率要高于截取字符串进行转换的操作
        char[] cs = str.toCharArray();
        cs[0] -= 32;
        return String.valueOf(cs);
    }

    public static String subLastStr(String str, String last) {
        return str.substring(0, str.lastIndexOf(last));
    }

    public static String toJsonStr(Object o) {
        try {
            return objectMapper.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            throw new ServiceException();
        }
    }

    public static String removePrefix(String prefix, String name) {
        return name.replaceFirst(prefix, "");
    }
}
