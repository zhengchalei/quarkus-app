package io.github.zhengchalei.module.system.service;

import com.querydsl.jpa.impl.JPAQuery;
import io.github.zhengchalei.common.RPage;
import io.github.zhengchalei.common.model.Page;
import io.github.zhengchalei.module.system.domain.QSysPermission;
import io.github.zhengchalei.module.system.domain.QSysRole;
import io.github.zhengchalei.module.system.domain.QSysUser;
import io.github.zhengchalei.module.system.domain.SysUser;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

/**
 * @author <a href="mailto:stone981023@gmail.com">zhengchalei</a>
 **/
@Singleton
@Transactional
public class SysUserService {

    @Inject
    EntityManager entityManager;

    public RPage<SysUser> findSysUserPage(Page page, SysUser params) {
        QSysUser qSysUser = QSysUser.sysUser;
        JPAQuery<SysUser> query = new JPAQuery<SysUser>(entityManager)
                .from(qSysUser)
                .leftJoin(qSysUser.roles, QSysRole.sysRole).fetchJoin()
                .leftJoin(QSysRole.sysRole.permissions, QSysPermission.sysPermission).fetchJoin();
        if (params.id != null) {
            query = query.where(qSysUser.id.eq(params.id));
        }
        if (params.username != null) {
            query = query.where(qSysUser.username.eq(params.username));
        }
        RPage<SysUser> res = new RPage<>();
        // 查询总数
        res.count = query.select(qSysUser.count()).fetchCount();
        // 分页查询, 构建条件后给分页内部fetch
        res.data = query.select(qSysUser).offset(page.skip()).limit(page.limit()).fetch();
        return res;
    }


    public SysUser findSysUserById(Long id) {
        QSysUser qSysUser = QSysUser.sysUser;
        SysUser data = new JPAQuery<>(entityManager)
                .select(qSysUser)
                .from(qSysUser)
                .leftJoin(qSysUser.roles, QSysRole.sysRole).fetchJoin()
                .leftJoin(QSysRole.sysRole.permissions, QSysPermission.sysPermission).fetchJoin()
                .where(qSysUser.id.eq(id))
                .fetchOne();
        if (data == null) {
            throw new NotFoundException();
        }
        return data;
    }


    public void saveSysUser(SysUser sysUser) {
        sysUser.persistAndFlush();
    }


    public void updateSysUserById(SysUser sysUser) {
        SysUser flush = SysUser.findById(sysUser.id);
        flush.username = sysUser.username;
        flush.email = sysUser.email;
        // change
        flush.isPersistent();
    }


    public boolean deleteSysUserById(Long id) {
        return SysUser.deleteById(id);
    }
}
