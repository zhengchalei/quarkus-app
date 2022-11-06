package io.github.zhengchalei.module.system.service;

import com.speedment.jpastreamer.application.JPAStreamer;
import io.github.zhengchalei.common.model.Page;
import io.github.zhengchalei.module.system.domain.SysUser;
import io.github.zhengchalei.module.system.domain.SysUser$;

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
public class SysUserService {

    @Inject
    JPAStreamer jpaStreamer;

    public long findCount(SysUser sysUser) {
        return SysUser.count();
    }

    public List<SysUser> findList(Page page, SysUser sysUser) {
        Stream<SysUser> stream = jpaStreamer
            .stream(SysUser.class)
            .sorted(SysUser$.username.reversed())
            .limit(page.limit())
            .skip(page.skip());
        if (sysUser.id != null) {
            stream = stream.filter(w -> w.id.equals(sysUser.id));
        }
        return stream.toList();
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
