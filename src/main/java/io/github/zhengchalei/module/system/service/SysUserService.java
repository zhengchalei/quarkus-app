package io.github.zhengchalei.module.system.service;

import io.github.zhengchalei.module.system.dto.SysUserDto;
import io.quarkus.panache.common.Page;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author <a href="mailto:stone981023@gmail.com">zhengchalei</a>
 **/
public interface SysUserService {

    List<SysUserDto> findPage(@NotNull Page page, @NotNull SysUserDto sysUserDto);

    long findCount(@NotNull Page page, @NotNull SysUserDto sysUserDto);

    long findCount();

    List<SysUserDto> findList(@NotNull SysUserDto sysUserDto);

    SysUserDto findById(@NotNull Long id);

    void save(@NotNull SysUserDto sysUserDto);

    void update(@NotNull Long id, @NotNull SysUserDto sysUserDto);

    boolean delete(@NotNull Long id);
}
