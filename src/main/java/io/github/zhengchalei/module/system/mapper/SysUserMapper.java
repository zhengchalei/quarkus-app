package io.github.zhengchalei.module.system.mapper;

import io.github.zhengchalei.module.system.domain.SysUser;
import io.github.zhengchalei.module.system.dto.SysUserDto;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * @author <a href="mailto:stone981023@gmail.com">zhengchalei</a>
 * @since 1.0.0
 **/
@Mapper(componentModel = "cdi")
public interface SysUserMapper {

    SysUserDto sysUserToSysUserDto(SysUser sysUser);

    SysUser sysUserDtoToSysUser(SysUserDto sysUserDto);

    List<SysUserDto> sysUserListToSysUserDtoList(List<SysUser> sysUser);

    List<SysUser> sysUserDtoListToSysUserList(List<SysUserDto> sysUserDto);
}
