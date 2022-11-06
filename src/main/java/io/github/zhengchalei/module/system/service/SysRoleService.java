package io.github.zhengchalei.module.system.service;

import io.github.zhengchalei.common.jpa.QueryBuilder;
import io.github.zhengchalei.module.system.domain.SysRole;
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
public class SysRoleService {

    @Inject
    EntityManager entityManager;

    private QueryBuilder<SysRole> queryBuilder(SysRole sysRole) {
        QueryBuilder<SysRole> queryBuilder = new QueryBuilder<>(entityManager, SysRole.class);
        if (sysRole.getId() != null) {
            Predicate predicate = queryBuilder.cb.equal(
                queryBuilder.root.get("id"),
                sysRole.getId()
            );
            queryBuilder.where(predicate);
        }
        return queryBuilder;
    }


    public List<SysRole> findPage(Page page, SysRole sysRole) {
        return SysRole.findAll().page(page).list();
    }


    public long findCount(Page page, SysRole sysRole) {
        return SysRole.findAll().page(page).count();
    }


    public List<SysRole> findAll(SysRole sysRole) {
        QueryBuilder<SysRole> queryBuilder = this.queryBuilder(sysRole);
        return queryBuilder.exec().getResultList();
    }


    public SysRole findById(Long id) {
        SysRole data = SysRole.findById(id);
        if (data == null) {
            throw new NotFoundException();
        }
        return data;
    }


    public void save(SysRole sysRole) {
        sysRole.persistAndFlush();
    }


    public void update(Long id, SysRole sysRole) {
        SysRole flush = findById(id);
        // change
        flush.persistAndFlush();
    }


    public boolean delete(Long id) {
        return SysRole.deleteById(id);
    }
}
