package io.github.zhengchalei.module.system.mapper;

import io.github.zhengchalei.module.system.domain.SysUser;
import io.github.zhengchalei.module.system.dto.SysUserDto;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SysUserMapper {
    SysUserMapper MAPPER = Mappers.getMapper(SysUserMapper.class);

    SysUser sysUserDtoToSysUser(SysUserDto sysUserDto);

    SysUserDto sysUserToSysUserDto(SysUser sysUser);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    SysUser updateSysUserFromSysUserDto(SysUserDto sysUserDto, @MappingTarget SysUser sysUser);
}
