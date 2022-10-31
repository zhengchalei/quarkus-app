package io.github.zhengchalei.module.system.graphql;

import io.github.zhengchalei.module.system.dto.SysUserDto;
import io.github.zhengchalei.module.system.service.SysUserService;
import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.Query;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.List;

/**
 * @author <a href="mailto:stone981023@gmail.com">zhengchalei</a>
 * @since 1.0.0
 **/
@GraphQLApi
@RequestScoped
public class SysUserGraphService {

    @Inject
    SysUserService sysUserService;

    @Query
    public List<SysUserDto> list(SysUserDto sysUserDto) {
        return this.sysUserService.findList(sysUserDto);
    }

    @Query
    public Long count() {
        return this.sysUserService.findCount();
    }

}
