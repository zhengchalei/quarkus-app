package io.github.zhengchalei.module.system.service.impl;

import com.speedment.jpastreamer.application.JPAStreamer;
import io.github.zhengchalei.module.system.domain.SysUser;
import io.github.zhengchalei.module.system.dto.SysUserDto;
import io.github.zhengchalei.module.system.mapper.SysUserMapper;
import io.github.zhengchalei.module.system.service.SysUserService;
import io.quarkus.panache.common.Page;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.transaction.Transactional;
import javax.ws.rs.NotFoundException;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author <a href="mailto:stone981023@gmail.com">zhengchalei</a>
 **/
@Singleton
@Transactional
public class SysUserServiceImpl implements SysUserService {

    @Inject
    JPAStreamer jpaStreamer;

    @Override
    public List<SysUserDto> findPage(Page page, SysUserDto sysUserDto) {
        List<SysUser> list = SysUser.<SysUser>findAll().page(page).list();
        return list.stream().map(SysUserMapper.MAPPER::sysUserToSysUserDto).toList();
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
    public List<SysUserDto> findList(SysUserDto dto) {
        Stream<SysUser> stream = jpaStreamer.stream(SysUser.class);
        if (dto.id != null) {
            stream = stream.filter(w -> w.id.equals(dto.id));
        }
        List<SysUser> list = stream.toList();
        return list.stream().map(SysUserMapper.MAPPER::sysUserToSysUserDto).toList();
    }

    @Override
    public SysUserDto findById(Long id) {
        SysUser data = SysUser.findById(id);
        if (data == null) {
            throw new NotFoundException();
        }
        return SysUserMapper.MAPPER.sysUserToSysUserDto(data);
    }

    @Override
    public void save(SysUserDto sysUserDto) {
        SysUser sysUser = SysUserMapper.MAPPER.sysUserDtoToSysUser(sysUserDto);
        sysUser.persistAndFlush();
    }

    @Override
    public void update(Long id, SysUserDto sysUserDto) {
        SysUser flush = SysUser.findById(id);
        flush.username = sysUserDto.username;
        flush.email = sysUserDto.email;
        // change
        flush.isPersistent();
    }

    @Override
    public boolean delete(Long id) {
        return SysUser.deleteById(id);
    }
}
