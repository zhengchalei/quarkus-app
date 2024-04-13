package io.github.zhengchalei.module.system.graphql;

import io.github.zhengchalei.common.RPage;
import io.github.zhengchalei.common.model.Page;
import io.github.zhengchalei.module.system.domain.SysRole;
import io.github.zhengchalei.module.system.domain.SysUser;
import io.github.zhengchalei.module.system.service.SysRoleService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.graphql.*;

import java.util.ArrayList;
import java.util.List;

/**
 * SysRoleGraphApi
 *
 * @author <a href="mailto:stone981023@gmail.com">zhengchalei</a>
 * @since 1.0.0
 **/
@GraphQLApi
@RequestScoped
public class SysRoleGraphApi {

    @Inject
    SysRoleService sysRoleService;

    @Query
    @Description("查询系统角色列表")
    public RPage<SysRole> findSysRolePage(Page page, SysRole params) {
        return this.sysRoleService.findSysRolePage(page, params);
    }

    @Query
    @Description("根据 id 查询系统角色")
    public SysRole findSysRoleById(Long id) {
        return this.sysRoleService.findSysRoleById(id);
    }

    @Mutation
    @Description("添加系统角色")
    public SysRole saveSysRole(SysRole sysRole) {
        this.sysRoleService.saveSysRole(sysRole);
        return this.sysRoleService.findById(sysRole.id);
    }

    @Mutation
    @Description("根据 id 修改系统角色")
    public SysRole updateSysRoleById(SysRole sysRole) {
        this.sysRoleService.updateSysRoleById(sysRole);
        return this.sysRoleService.findById(sysRole.id);
    }

    @Mutation
    @Description("根据 id 删除系统角色")
    public boolean deleteSysRoleById(Long id) {
        return this.sysRoleService.deleteSysRoleById(id);
    }

}
