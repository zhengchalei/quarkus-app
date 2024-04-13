package io.github.zhengchalei.module.system.service;

import com.querydsl.jpa.impl.JPAQuery;
import io.github.zhengchalei.common.RPage;
import io.github.zhengchalei.common.exception.ServiceException;
import io.github.zhengchalei.common.model.Page;
import io.github.zhengchalei.module.system.domain.*;
import io.quarkus.runtime.util.StringUtil;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

/**
 * @author <a href="mailto:stone981023@gmail.com">zhengchalei</a>
 **/
@Singleton
@Transactional
public class SysRoleService {

    @Inject
    EntityManager entityManager;

    public RPage<SysRole> findSysRolePage(Page page, SysRole params) {
        QSysRole qSysRole = QSysRole.sysRole;
        JPAQuery<SysRole> query = new JPAQuery<SysRole>(entityManager)
                .select(qSysRole)
                .from(qSysRole)
                .leftJoin(qSysRole.permissions, QSysPermission.sysPermission).fetchJoin();

        if (StringUtil.isNullOrEmpty(params.code)) {
            query = query.where(qSysRole.code.eq(params.code));
        }
        if (StringUtil.isNullOrEmpty(params.name)) {
            query = query.where(qSysRole.name.eq(params.name));
        }

        RPage<SysRole> res = new RPage<>();
        res.count = query.select(qSysRole.count()).fetchCount();
        // 分页查询, 构建条件后给分页内部fetch
        res.data = query.select(qSysRole).offset(page.skip()).limit(page.limit()).fetch();
        return res;
    }

    public SysRole findById(Long id) {
        SysRole data = SysRole.findById(id);
        if (data == null) {
            throw new NotFoundException();
        }
        return data;
    }


    public void saveSysRole(SysRole sysRole) {
        // 判断角色名是否重复
        if (SysRole.count("name = ?1", sysRole.name) > 0) {
            throw new ServiceException("角色名重复");
        }
        // 判断 code
        if (SysRole.count("code = ?1", sysRole.code) > 0) {
            throw new ServiceException("角色编码重复");
        }
        sysRole.persistAndFlush();
    }


    public void updateSysRoleById(SysRole sysRole) {
        // 判断角色名是否重复
        Optional.<SysRole>of(SysRole.find("name = ?1", sysRole.name).firstResult()).ifPresent(data -> {
            if (!data.id.equals(sysRole.id)) {
                throw new ServiceException("角色名重复");
            }
        });
        // 判断 code
        Optional.<SysRole>of(SysRole.find("code = ?1", sysRole.code).firstResult()).ifPresent(data -> {
            if (!data.id.equals(sysRole.id)) {
                throw new ServiceException("角色编码重复");
            }
        });

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


    public boolean deleteSysRoleById(Long id) {
        // 判断角色是否已经关联用户
        QSysRole qSysRole = QSysRole.sysRole;
        Integer userCount = new JPAQuery<Integer>(entityManager)
                .select(qSysRole.users.size())
                .from(qSysRole)
                .where(qSysRole.id.eq(id))
                .fetchFirst();
        if (userCount == null || userCount > 0) {
            throw new ServiceException("角色已经关联用户, 不能删除");
        }
        return SysRole.deleteById(id);
    }

    public SysRole findSysRoleById(Long id) {
        return SysRole.findById(id);
    }
}
