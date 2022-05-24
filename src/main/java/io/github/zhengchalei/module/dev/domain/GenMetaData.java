package io.github.zhengchalei.module.dev.domain;

import io.github.zhengchalei.common.Util;
import io.github.zhengchalei.common.model.TreeNode;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.ws.rs.QueryParam;

/**
 * GeneratorMetaData
 *
 * @author zhengchalei
 */
@Schema(title = "代码生成")
public class GenMetaData {
    /**
     * 树
     */
    @Schema(hidden = true)
    public boolean tree;
    /**
     * 实体
     */
    @Schema(hidden = true)
    public Class<?> entity;
    @Schema(hidden = true)
    public String entityName;
    @Schema(hidden = true)
    public String lowerCaseEntityName;
    @Schema(hidden = true)
    public String entityPath;
    @Schema(hidden = true)
    public String modulePath;

    /**
     * 文件已存在时重写
     */
    @QueryParam("classzz")
    @Schema(title = "类路径", defaultValue = "io.github.zhengchalei.module.system.domain.SysDepartment")
    public String classzz;

    @QueryParam("rewrite")
    @Schema(title = "重写", defaultValue = "false")
    public boolean rewrite;

    /**
     * 模块
     */
    @QueryParam("module")
    @Schema(title = "所属模块", defaultValue = "system")
    public String module;

    public GenMetaData() {
    }

    public GenMetaData build(Class<?> entity) {
        this.tree = TreeNode.class.isAssignableFrom(entity);
        this.entity = entity;
        this.entityName = entity.getSimpleName();
        this.lowerCaseEntityName = Util.firstLowerCase(entityName);
        this.entityPath = entity.getPackageName();
        this.modulePath = Util.subLastStr(entityPath, ".domain");
        return this;
    }
}
