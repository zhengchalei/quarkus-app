package io.github.zhengchalei.module.system.service;

import io.github.zhengchalei.common.jpa.QueryBuilder;
import io.github.zhengchalei.common.model.Page;
import io.github.zhengchalei.module.system.domain.SysPermission;
import io.github.zhengchalei.module.system.domain.SysRole;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.Predicate;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import java.util.Collection;
import java.util.HashSet;
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

    public List<SysRole> findList(Page page, SysRole sysRole) {
        return null;
    }

    public long findCount(SysRole sysRole) {
        return SysRole.findAll().count();
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


    public void update(SysRole sysRole) {
        SysRole flush = findById(sysRole.id);
        flush.name = sysRole.name;
        flush.code = sysRole.code;
        flush.description = sysRole.description;
        List<Long> permissionIds = sysRole.permissions.stream().map(SysPermission::getId).toList();
        if (permissionIds.isEmpty()) {
            Collection<SysPermission> permissions = SysPermission
                .<SysPermission>find("id in (?)", permissionIds)
                .list();
            flush.permissions = new HashSet<>(permissions);
        }
        flush.persistAndFlush();
    }


    public boolean delete(Long id) {
        return SysRole.deleteById(id);
    }

}
