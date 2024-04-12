package io.github.zhengchalei.module.system.service;

import com.querydsl.jpa.impl.JPAQuery;
import io.github.zhengchalei.common.model.Page;
import io.github.zhengchalei.module.system.domain.*;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import java.util.List;
import java.util.Set;

/**
 * @author <a href="mailto:stone981023@gmail.com">zhengchalei</a>
 **/
@Singleton
@Transactional
public class SysUserService {

    @Inject
    EntityManager entityManager;

    public long findCount(SysUser sysUser) {
        QSysUser qSysUser = QSysUser.sysUser;
        JPAQuery<Long> query = new JPAQuery<SysUser>(entityManager)
            .select(qSysUser.count())
            .join(QSysRole.sysRole)
            .join(QSysPermission.sysPermission)
            .from(qSysUser);
        if (sysUser.id != null) {
            query = query.where(qSysUser.id.eq(sysUser.id));
        }
        if (sysUser.username != null) {
            query = query.where(qSysUser.username.eq(sysUser.username));
        }
        return query.fetchFirst();
    }

    public List<SysUser> findList(Page page, SysUser sysUser) {
        QSysUser qSysUser = QSysUser.sysUser;
        JPAQuery<SysUser> query = new JPAQuery<SysUser>(entityManager)
            .from(qSysUser);
        if (sysUser.id != null) {
            query = query.where(qSysUser.id.eq(sysUser.id));
        }
        if (sysUser.username != null) {
            query = query.where(qSysUser.username.eq(sysUser.username));
        }
        return page.fetch(query);
    }


    public SysUser findById(Long id) {
        SysUser data = SysUser.findById(id);
        if (data == null) {
            throw new NotFoundException();
        }
        return data;
    }


    public void save(SysUser sysUser) {
        sysUser.persistAndFlush();
    }


    public void update(SysUser sysUser) {
        SysUser flush = SysUser.findById(sysUser.id);
        flush.username = sysUser.username;
        flush.email = sysUser.email;
        // change
        flush.isPersistent();
    }


    public boolean delete(Long id) {
        return SysUser.deleteById(id);
    }
}
