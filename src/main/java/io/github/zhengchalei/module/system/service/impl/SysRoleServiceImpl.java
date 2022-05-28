package io.github.zhengchalei.module.system.service.impl;

import io.github.zhengchalei.common.jpa.QueryBuilder;
import io.github.zhengchalei.module.system.domain.SysRole;
import io.github.zhengchalei.module.system.service.SysRoleService;
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
public class SysRoleServiceImpl implements SysRoleService {

    @Inject
    EntityManager entityManager;

    private QueryBuilder<SysRole> queryBuilder(SysRole sysRole) {
        QueryBuilder<SysRole> queryBuilder = new QueryBuilder<>(entityManager, SysRole.class);
        if (sysRole.id != null) {
            Predicate predicate = queryBuilder.cb.equal(
                    queryBuilder.root.get("id"),
                    sysRole.id
            );
            queryBuilder.where(predicate);
        }
        return queryBuilder;
    }

    @Override
    public List<SysRole> findPage(Page page, SysRole sysRole) {
        return SysRole.findAll().page(page).list();
    }

    @Override
    public long findCount(Page page, SysRole sysRole) {
        return SysRole.findAll().page(page).count();
    }

    @Override
    public List<SysRole> findAll(SysRole sysRole) {
        QueryBuilder<SysRole> queryBuilder = this.queryBuilder(sysRole);
        return queryBuilder.exec().getResultList();
    }

    @Override
    public SysRole findById(Long id) {
        SysRole data = SysRole.findById(id);
        if (data == null) {
            throw new NotFoundException();
        }
        return data;
    }

    @Override
    public void save(SysRole sysRole) {
        sysRole.persistAndFlush();
    }

    @Override
    public void update(Long id, SysRole sysRole) {
        SysRole flush = findById(id);
        // change
        flush.persistAndFlush();
    }

    @Override
    public boolean delete(Long id) {
        return SysRole.deleteById(id);
    }
}
