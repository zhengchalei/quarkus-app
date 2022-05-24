package io.github.zhengchalei.module.system.service.impl;

import io.github.zhengchalei.common.jpa.QueryBuilder;
import io.github.zhengchalei.module.system.domain.SysUser;
import io.github.zhengchalei.module.system.domain.SysUser_;
import io.github.zhengchalei.module.system.service.SysUserService;
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
public class SysUserServiceImpl implements SysUserService {

    @Inject
    EntityManager entityManager;

    private QueryBuilder<SysUser> queryBuilder(SysUser sysUser) {
        QueryBuilder<SysUser> queryBuilder = new QueryBuilder<>(entityManager, SysUser.class);
        if (sysUser.id != null) {
            Predicate predicate = queryBuilder.cb.equal(
                    queryBuilder.root.get(SysUser_.id),
                    sysUser.id
            );
            queryBuilder.where(predicate);
        }
        return queryBuilder;
    }

    @Override
    public List<SysUser> findPage(Page page, SysUser sysUser) {
        return SysUser.findAll().page(page).list();
    }

    @Override
    public long findCount(Page page, SysUser sysUser) {
        return SysUser.findAll().page(page).count();
    }

    @Override
    public List<SysUser> findAll(SysUser sysUser) {
        QueryBuilder<SysUser> queryBuilder = this.queryBuilder(sysUser);
        return queryBuilder.exec().getResultList();
    }


    @Override
    public SysUser findById(Long id) {
        SysUser data = SysUser.findById(id);
        if (data == null) {
            throw new NotFoundException();
        }
        return data;
    }

    @Override
    public void save(SysUser sysUser) {
        sysUser.persistAndFlush();
    }

    @Override
    public void update(SysUser sysUser) {
        SysUser flush = findById(sysUser.id);
        // change
        flush.persistAndFlush();
    }

    @Override
    public boolean delete(Long id) {
        return SysUser.deleteById(id);
    }
}
