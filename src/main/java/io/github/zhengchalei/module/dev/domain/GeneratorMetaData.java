package io.github.zhengchalei.module.dev.domain;

import io.github.zhengchalei.common.Util;

/**
 * GeneratorMetaData
 *
 * @author zhengchalei
 */
public class GeneratorMetaData {
    /**
     * 树
     */
    public final boolean tree;
    /**
     * 实体
     */
    public final Class<?> entity;
    /**
     * 模块
     */
    public String module;
    public String entityName;
    public String lowerCaseEntityName;
    public String entityPath;
    public String modulePath;

    public GeneratorMetaData(Class<?> entity) {
        this(false, entity);
    }

    public GeneratorMetaData(boolean tree, Class<?> entity) {
        this.tree = tree;
        this.entity = entity;

        this.module = "system";
        this.entityName = entity.getSimpleName();
        this.lowerCaseEntityName = Util.firstLowerCase(entityName);
        this.entityPath = entity.getPackageName();
        this.modulePath = Util.subLastStr(entityPath, ".domain");
    }
}
