package io.github.zhengchalei.module.system.mapper;

import io.github.zhengchalei.module.system.domain.SysDepartment;
import io.github.zhengchalei.module.system.dto.SysDepartmentDto;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SysDepartmentMapper {
    SysDepartmentMapper MAPPER = Mappers.getMapper(SysDepartmentMapper.class);

    SysDepartment sysDepartmentDtoToSysDepartment(SysDepartmentDto sysDepartmentDto);

    SysDepartmentDto sysDepartmentToSysDepartmentDto(SysDepartment sysDepartment);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    SysDepartment updateSysDepartmentFromSysDepartmentDto(SysDepartmentDto sysDepartmentDto, @MappingTarget SysDepartment sysDepartment);

}
