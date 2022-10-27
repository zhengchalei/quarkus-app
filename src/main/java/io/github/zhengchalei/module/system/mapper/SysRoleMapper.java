package io.github.zhengchalei.module.system.mapper;

import io.github.zhengchalei.module.system.domain.SysRole;
import io.github.zhengchalei.module.system.dto.SysRoleDto;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SysRoleMapper {
    SysRoleMapper MAPPER = Mappers.getMapper(SysRoleMapper.class);

    SysRole sysRoleDtoToSysRole(SysRoleDto sysRoleDto);

    SysRoleDto sysRoleToSysRoleDto(SysRole sysRole);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    SysRole updateSysRoleFromSysRoleDto(SysRoleDto sysRoleDto, @MappingTarget SysRole sysRole);
}
