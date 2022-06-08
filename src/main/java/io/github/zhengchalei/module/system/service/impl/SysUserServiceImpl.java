package io.github.zhengchalei.module.system.service.impl;

import com.querydsl.jpa.impl.JPAQuery;
import io.github.zhengchalei.common.jpa.QueryBuilder;
import io.github.zhengchalei.module.system.domain.SysUser;
import io.github.zhengchalei.module.system.dto.SysUserDto;
import io.github.zhengchalei.module.system.mapper.SysUserMapper;
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

    @Inject
    SysUserMapper sysUserMapper;

    private QueryBuilder<SysUser> queryBuilder(SysUser sysUser) {
        QueryBuilder<SysUser> queryBuilder = new QueryBuilder<>(entityManager, SysUser.class);
        if (sysUser.id != null) {
            Predicate predicate = queryBuilder.cb.equal(
                    queryBuilder.root.get("id"),
                    sysUser.id
            );
            queryBuilder.where(predicate);
        }
        return queryBuilder;
    }

    @Override
    public List<SysUserDto> findPage(Page page, SysUserDto sysUserDto) {
        List<SysUser> list = SysUser.<SysUser>findAll().page(page).list();
        return this.sysUserMapper.sysUserListToSysUserDtoList(list);
    }

    @Override
    public long findCount(Page page, SysUserDto sysUserDto) {
        return SysUser.findAll().page(page).count();
    }

    @Override
    public long findCount() {
        return SysUser.findAll().count();
    }

    @Override
    public List<SysUserDto> findList(SysUserDto sysUserDto) {
        SysUser sysUser = this.sysUserMapper.sysUserDtoToSysUser(sysUserDto);
        QueryBuilder<SysUser> queryBuilder = this.queryBuilder(sysUser);
        List<SysUser> list = queryBuilder.exec().getResultList();
        return this.sysUserMapper.sysUserListToSysUserDtoList(list);
    }

    @Override
    public SysUserDto findById(Long id) {
        SysUser data = SysUser.findById(id);
        if (data == null) {
            throw new NotFoundException();
        }
        return this.sysUserMapper.sysUserToSysUserDto(data);
    }

    @Override
    public void save(SysUserDto sysUserDto) {
        SysUser sysUser = this.sysUserMapper.sysUserDtoToSysUser(sysUserDto);
        sysUser.persistAndFlush();
    }

    @Override
    public void update(Long id, SysUserDto sysUserDto) {
        SysUser data = this.sysUserMapper.sysUserDtoToSysUser(sysUserDto);
        if (data == null) {
            throw new NotFoundException();
        }
        // change
        data.persistAndFlush();
    }

    @Override
    public boolean delete(Long id) {
        return SysUser.deleteById(id);
    }
}
