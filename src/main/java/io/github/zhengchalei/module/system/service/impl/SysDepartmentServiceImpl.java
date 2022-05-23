package io.github.zhengchalei.module.system.service.impl;

import io.github.zhengchalei.common.jpa.QueryBuilder;
import io.github.zhengchalei.common.model.Util;
import io.github.zhengchalei.module.system.domain.SysDepartment;
import io.github.zhengchalei.module.system.domain.SysDepartment_;
import io.github.zhengchalei.module.system.service.SysDepartmentService;
import io.quarkus.panache.common.Page;
import org.apache.commons.lang3.StringUtils;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.criteria.Predicate;
import javax.transaction.Transactional;
import javax.ws.rs.NotFoundException;
import java.util.List;

/**
 * @author <a href="mailto:stone981023@gmail.com">zhengchalei</a>
 **/
@Singleton
@Transactional
public class SysDepartmentServiceImpl implements SysDepartmentService {

    @Inject
    EntityManager entityManager;

    private QueryBuilder<SysDepartment> queryBuilder(SysDepartment sysDepartment) {
        QueryBuilder<SysDepartment> queryBuilder = new QueryBuilder<>(entityManager, SysDepartment.class);
        if (StringUtils.isNoneBlank(sysDepartment.name)) {
            Predicate predicate = queryBuilder.cb.like(
                    queryBuilder.root.get(SysDepartment_.name),
                    StringUtils.join(sysDepartment.name, "%")
            );
            queryBuilder.where(predicate);
        }
        if (StringUtils.isNoneBlank(sysDepartment.description)) {
            Predicate predicate = queryBuilder.cb.like(
                    queryBuilder.root.get(SysDepartment_.description),
                    StringUtils.join(sysDepartment.description, "%")
            );
            queryBuilder.where(predicate);
        }
        return queryBuilder;
    }

    @Override
    public List<SysDepartment> findPage(Page page, SysDepartment sysDepartment) {
        return SysDepartment.findAll().page(page).list();
    }

    @Override
    public long findCount(Page page, SysDepartment sysDepartment) {
        return SysDepartment.findAll().page(page).count();
    }

    @Override
    public List<SysDepartment> findAll(SysDepartment sysDepartment) {
        QueryBuilder<SysDepartment> queryBuilder = this.queryBuilder(sysDepartment);
        return queryBuilder.exec().getResultList();
    }

    @Override
    public List<SysDepartment> tree() {
        List<SysDepartment> listAll = SysDepartment.listAll();
        return Util.tree(listAll);
    }

    @Override
    public SysDepartment findById(Long id) {
        SysDepartment data = SysDepartment.findById(id);
        if (data == null) {
            throw new NotFoundException();
        }
        return data;
    }

    @Override
    public void save(SysDepartment sysDepartment) {
        sysDepartment.persistAndFlush();
    }

    @Override
    public void update(SysDepartment sysDepartment) {
        SysDepartment flush = findById(sysDepartment.id);
        flush.parentId = sysDepartment.parentId;
        flush.sort = sysDepartment.sort;
        flush.children = sysDepartment.children;
        flush.persistAndFlush();
    }

    @Override
    public boolean delete(Long id) {
        return SysDepartment.deleteById(id);
    }
}
