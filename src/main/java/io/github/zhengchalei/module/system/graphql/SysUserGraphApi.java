package io.github.zhengchalei.module.system.graphql;

import io.github.zhengchalei.common.RPage;
import io.github.zhengchalei.common.model.Page;
import io.github.zhengchalei.module.system.domain.SysUser;
import io.github.zhengchalei.module.system.service.SysUserService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.graphql.Description;
import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.Mutation;
import org.eclipse.microprofile.graphql.Query;

/**
 * SysUserGraphApi
 *
 * @author <a href="mailto:stone981023@gmail.com">zhengchalei</a>
 * @since 1.0.0
 **/
@GraphQLApi
@RequestScoped
public class SysUserGraphApi {

    @Inject
    SysUserService sysUserService;

    @Query
    @Description("查询系统用户列表")
    public RPage<SysUser> findSysUserPage(Page page, SysUser params) {
        return this.sysUserService.findSysUserPage(page, params);
    }

    @Query
    @Description("根据 id 查询系统用户")
    public SysUser findSysUserById(Long id) {
        return this.sysUserService.findSysUserById(id);
    }

    @Mutation
    @Description("添加系统用户")
    public SysUser saveSysUser(SysUser sysUser) {
        this.sysUserService.saveSysUser(sysUser);
        return this.sysUserService.findSysUserById(sysUser.id);
    }

    @Mutation
    @Description("根据 id 修改系统用户")
    public SysUser updateSysUserById(SysUser sysUser) {
        this.sysUserService.updateSysUserById(sysUser);
        return this.sysUserService.findSysUserById(sysUser.id);
    }

    @Mutation
    @Description("根据 id 删除系统用户")
    public boolean deleteSysUserById(Long id) {
        return this.sysUserService.deleteSysUserById(id);
    }

    @Mutation
    @Description("根据 id 激活系统用户")
    public boolean activeSysUserById(Long id) {
        return this.sysUserService.activeSysUserById(id);
    }

    @Mutation
    @Description("根据 id 禁用系统用户")
    public boolean disableSysUserById(Long id) {
        return this.sysUserService.disableSysUserById(id);
    }


}
