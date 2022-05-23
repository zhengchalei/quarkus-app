package io.github.zhengchalei.module.system.service;

import io.github.zhengchalei.module.system.domain.SysDepartment;
import io.quarkus.panache.common.Page;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author <a href="mailto:stone981023@gmail.com">zhengchalei</a>
 **/
public interface SysDepartmentService {

    List<SysDepartment> findPage(Page page, SysDepartment sysDepartment);

    long findCount(Page page, SysDepartment sysDepartment);

    List<SysDepartment> findAll(SysDepartment sysDepartment);

    List<SysDepartment> tree();

    SysDepartment findById(@Valid @NotNull Long id);

    void save(@Valid SysDepartment sysDepartment);

    void update(@Valid SysDepartment sysDepartment);

    boolean delete(@Valid @NotNull Long id);
}
