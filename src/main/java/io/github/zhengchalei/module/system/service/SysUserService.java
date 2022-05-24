package io.github.zhengchalei.module.system.service;

import io.github.zhengchalei.module.system.domain.SysUser;
import io.quarkus.panache.common.Page;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author <a href="mailto:stone981023@gmail.com">zhengchalei</a>
 **/
public interface SysUserService {

    List<SysUser> findPage(Page page, SysUser sysUser);

    long findCount(Page page, SysUser sysUser);

    List<SysUser> findAll(SysUser sysUser);


    SysUser findById(@Valid @NotNull Long id);

    void save(@Valid SysUser sysUser);

    void update(@Valid SysUser sysUser);

    boolean delete(@Valid @NotNull Long id);
}
