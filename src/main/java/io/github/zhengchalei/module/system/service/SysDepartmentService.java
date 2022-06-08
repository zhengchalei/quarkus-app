package io.github.zhengchalei.module.system.service;

import io.github.zhengchalei.module.system.domain.SysDepartment;
import io.quarkus.panache.common.Page;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author <a href="mailto:stone981023@gmail.com">zhengchalei</a>
 **/
public interface SysDepartmentService {

    List<SysDepartment> findPage(@NotNull Page page, @NotNull SysDepartment sysDepartment);

    long findCount(@NotNull Page page,@NotNull  SysDepartment sysDepartment);

    List<SysDepartment> findAll(@NotNull SysDepartment sysDepartment);

    List<SysDepartment> tree();

    SysDepartment findById(@NotNull Long id);

    void save(@NotNull SysDepartment sysDepartment);

    void update(@NotNull Long id, @NotNull SysDepartment sysDepartment);

    boolean delete(@NotNull Long id);
}
