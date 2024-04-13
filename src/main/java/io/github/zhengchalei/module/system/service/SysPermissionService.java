package io.github.zhengchalei.module.system.service;

import io.github.zhengchalei.common.jpa.QueryBuilder;
import io.github.zhengchalei.module.system.domain.SysPermission;
import io.quarkus.panache.common.Page;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.Predicate;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

import java.util.List;

/**
 * @author <a href="mailto:stone981023@gmail.com">zhengchalei</a>
 **/
@Singleton
@Transactional
public class SysPermissionService {

    @Inject
    EntityManager entityManager;

    private QueryBuilder<SysPermission> queryBuilder(SysPermission sysPermission) {
        QueryBuilder<SysPermission> queryBuilder = new QueryBuilder<>(entityManager, SysPermission.class);
        if (sysPermission.getId() != null) {
            Predicate predicate = queryBuilder.cb.equal(
                    queryBuilder.root.get("id"),
                    sysPermission.getId()
            );
            queryBuilder.where(predicate);
        }
        return queryBuilder;
    }


    public List<SysPermission> findPage(Page page, SysPermission sysPermission) {
        return SysPermission.findAll().page(page).list();
    }


    public long findCount(Page page, SysPermission sysPermission) {
        return SysPermission.findAll().page(page).count();
    }


    public List<SysPermission> findAll(SysPermission sysPermission) {
        QueryBuilder<SysPermission> queryBuilder = this.queryBuilder(sysPermission);
        return queryBuilder.exec().getResultList();
    }

    public SysPermission findById(Long id) {
        SysPermission data = SysPermission.findById(id);
        if (data == null) {
            throw new NotFoundException();
        }
        return data;
    }

    public void save(SysPermission sysPermission) {
        sysPermission.persistAndFlush();
    }


    public void update(Long id, SysPermission sysPermission) {
        SysPermission flush = findById(id);
        // change
        flush.persistAndFlush();
    }


    public boolean delete(Long id) {
        return SysPermission.deleteById(id);
    }
}
