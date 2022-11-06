package io.github.zhengchalei.module.system.graphql;

import io.github.zhengchalei.common.model.Page;
import io.github.zhengchalei.module.system.domain.SysUser;
import io.github.zhengchalei.module.system.service.SysUserService;
import org.eclipse.microprofile.graphql.Description;
import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.Mutation;
import org.eclipse.microprofile.graphql.Query;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.List;

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
    public List<SysUser> userList(Page page, SysUser sysUser) {
        return this.sysUserService.findList(page, sysUser);
    }

    @Query
    @Description("根据 id 查询系统用户")
    public SysUser userById(Long id) {
        return this.sysUserService.findById(id);
    }

    @Query
    @Description("查询系统用户数量")
    public Long userCount(SysUser sysUser) {
        return this.sysUserService.findCount(sysUser);
    }

    @Mutation
    @Description("添加系统用户")
    public SysUser addUser(SysUser sysUser) {
        this.sysUserService.save(sysUser);
        return this.sysUserService.findById(sysUser.id);
    }

    @Mutation
    @Description("修改系统用户")
    public SysUser updateUser(SysUser sysUser) {
        this.sysUserService.update(sysUser);
        return this.sysUserService.findById(sysUser.id);
    }

    @Mutation
    @Description("删除系统用户")
    public Boolean deleteUser(Long id) {
        return this.sysUserService.delete(id);
    }

}
