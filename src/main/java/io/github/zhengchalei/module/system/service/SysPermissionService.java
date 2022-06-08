package io.github.zhengchalei.module.system.service;

import io.github.zhengchalei.module.system.domain.SysPermission;
import io.quarkus.panache.common.Page;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author <a href="mailto:stone981023@gmail.com">zhengchalei</a>
 **/
public interface SysPermissionService {

    List<SysPermission> findPage(@NotNull Page page, @NotNull SysPermission sysPermission);

    long findCount(@NotNull Page page, @NotNull SysPermission sysPermission);

    List<SysPermission> findAll(SysPermission sysPermission);

    List<SysPermission> tree();

    SysPermission findById(@NotNull Long id);

    void save(@NotNull SysPermission sysPermission);

    void update(@NotNull Long id,@NotNull  SysPermission sysPermission);

    boolean delete(@NotNull Long id);
}
