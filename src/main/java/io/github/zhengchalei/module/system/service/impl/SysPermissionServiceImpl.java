package io.github.zhengchalei.module.system.service.impl;

import io.github.zhengchalei.common.jpa.QueryBuilder;
import io.github.zhengchalei.module.system.domain.SysPermission;
import io.github.zhengchalei.module.system.domain.SysPermission_;
import io.github.zhengchalei.module.system.service.SysPermissionService;
import io.quarkus.panache.common.Page;

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
public class SysPermissionServiceImpl implements SysPermissionService {

    @Inject
    EntityManager entityManager;

    private QueryBuilder<SysPermission> queryBuilder(SysPermission sysPermission) {
        QueryBuilder<SysPermission> queryBuilder = new QueryBuilder<>(entityManager, SysPermission.class);
        if (sysPermission.id != null) {
            Predicate predicate = queryBuilder.cb.equal(
                    queryBuilder.root.get(SysPermission_.id),
                    sysPermission.id
            );
            queryBuilder.where(predicate);
        }
        return queryBuilder;
    }

    @Override
    public List<SysPermission> findPage(Page page, SysPermission sysPermission) {
        return SysPermission.findAll().page(page).list();
    }

    @Override
    public long findCount(Page page, SysPermission sysPermission) {
        return SysPermission.findAll().page(page).count();
    }

    @Override
    public List<SysPermission> findAll(SysPermission sysPermission) {
        QueryBuilder<SysPermission> queryBuilder = this.queryBuilder(sysPermission);
        return queryBuilder.exec().getResultList();
    }


    @Override
    public SysPermission findById(Long id) {
        SysPermission data = SysPermission.findById(id);
        if (data == null) {
            throw new NotFoundException();
        }
        return data;
    }

    @Override
    public void save(SysPermission sysPermission) {
        sysPermission.persistAndFlush();
    }

    @Override
    public void update(SysPermission sysPermission) {
        SysPermission flush = findById(sysPermission.id);
        // change
        flush.persistAndFlush();
    }

    @Override
    public boolean delete(Long id) {
        return SysPermission.deleteById(id);
    }
}
