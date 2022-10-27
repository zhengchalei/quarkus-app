package io.github.zhengchalei.module.system.mapper;

import io.github.zhengchalei.module.system.domain.SysPermission;
import io.github.zhengchalei.module.system.dto.SysPermissionDto;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SysPermissionMapper {
    SysPermissionMapper MAPPER = Mappers.getMapper(SysPermissionMapper.class);

    SysPermission sysPermissionDtoToSysPermission(SysPermissionDto sysPermissionDto);

    SysPermissionDto sysPermissionToSysPermissionDto(SysPermission sysPermission);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    SysPermission updateSysPermissionFromSysPermissionDto(SysPermissionDto sysPermissionDto, @MappingTarget SysPermission sysPermission);
}
