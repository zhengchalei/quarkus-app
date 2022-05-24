package io.github.zhengchalei.module.system.service;

import io.github.zhengchalei.module.system.domain.SysRole;
import io.quarkus.panache.common.Page;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author <a href="mailto:stone981023@gmail.com">zhengchalei</a>
 **/
public interface SysRoleService {

    List<SysRole> findPage(Page page, SysRole sysRole);

    long findCount(Page page, SysRole sysRole);

    List<SysRole> findAll(SysRole sysRole);


    SysRole findById(@Valid @NotNull Long id);

    void save(@Valid SysRole sysRole);

    void update(@Valid @NotNull Long id, @Valid SysRole sysRole);

    boolean delete(@Valid @NotNull Long id);
}
