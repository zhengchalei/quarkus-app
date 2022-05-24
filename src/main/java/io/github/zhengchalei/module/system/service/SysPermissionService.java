package io.github.zhengchalei.module.system.service;

import io.github.zhengchalei.module.system.domain.SysPermission;
import io.quarkus.panache.common.Page;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author <a href="mailto:stone981023@gmail.com">zhengchalei</a>
 **/
public interface SysPermissionService {

    List<SysPermission> findPage(Page page, SysPermission sysPermission);

    long findCount(Page page, SysPermission sysPermission);

    List<SysPermission> findAll(SysPermission sysPermission);

    List<SysPermission> tree();

    SysPermission findById(@Valid @NotNull Long id);

    void save(@Valid SysPermission sysPermission);

    void update(@Valid @NotNull Long id, @Valid SysPermission sysPermission);

    boolean delete(@Valid @NotNull Long id);
}
