package io.github.zhengchalei.module.dev.domain;

import io.github.zhengchalei.common.model.BaseEntity;
import io.github.zhengchalei.common.model.Util;

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
    public final Class<? extends BaseEntity> entity;
    /**
     * 模块
     */
    public String module;
    public String entityName;
    public String lowerCaseEntityName;
    public String entityPackage;
    public String packagePath;

    public GeneratorMetaData(Class<? extends BaseEntity> entity) {
        this(false, entity);
    }

    public GeneratorMetaData(boolean tree, Class<? extends BaseEntity> entity) {
        this.tree = tree;
        this.entity = entity;

        this.module = "system";
        this.entityName = entity.getSimpleName();
        this.lowerCaseEntityName = Util.firstLowerCase(entityName);
        this.entityPackage = entity.getPackageName();
        this.packagePath = Util.subLastStr(entityPackage, ".domain");
    }
}
