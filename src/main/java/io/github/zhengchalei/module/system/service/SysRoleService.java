package io.github.zhengchalei.module.system.service;

import io.github.zhengchalei.module.system.domain.SysRole;
import io.quarkus.panache.common.Page;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author <a href="mailto:stone981023@gmail.com">zhengchalei</a>
 **/
public interface SysRoleService {

    List<SysRole> findPage(@NotNull Page page, @NotNull SysRole sysRole);

    long findCount(@NotNull Page page, @NotNull SysRole sysRole);

    List<SysRole> findAll(@NotNull SysRole sysRole);

    SysRole findById(@NotNull Long id);

    void save(@NotNull SysRole sysRole);

    void update(@NotNull Long id,@NotNull  SysRole sysRole);

    boolean delete(@NotNull Long id);
}
