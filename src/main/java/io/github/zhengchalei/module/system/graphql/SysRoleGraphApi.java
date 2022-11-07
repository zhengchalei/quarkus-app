package io.github.zhengchalei.module.system.graphql;

import io.github.zhengchalei.common.model.Page;
import io.github.zhengchalei.module.system.domain.SysRole;
import io.github.zhengchalei.module.system.service.SysRoleService;
import org.eclipse.microprofile.graphql.Description;
import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.Mutation;
import org.eclipse.microprofile.graphql.Query;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
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
    @Description("查询系统用户列表")
    public List<SysRole> roleList(Page page, SysRole sysRole) {
        return this.sysRoleService.findList(page, sysRole);
    }

    @Query
    @Description("根据 id 查询系统用户")
    public SysRole roleById(Long id) {
        return this.sysRoleService.findById(id);
    }

    @Query
    @Description("查询系统用户数量")
    public Long roleCount(SysRole sysRole) {
        return this.sysRoleService.findCount(sysRole);
    }

    @Mutation
    @Description("添加系统用户")
    public SysRole addRole(SysRole sysRole) {
        this.sysRoleService.save(sysRole);
        return this.sysRoleService.findById(sysRole.id);
    }

    @Mutation
    @Description("修改系统用户")
    public SysRole updateRole(SysRole sysRole) {
        this.sysRoleService.update(sysRole);
        return this.sysRoleService.findById(sysRole.id);
    }

    @Mutation
    @Description("删除系统用户")
    public Boolean deleteRole(Long id) {
        return this.sysRoleService.delete(id);
    }

}
