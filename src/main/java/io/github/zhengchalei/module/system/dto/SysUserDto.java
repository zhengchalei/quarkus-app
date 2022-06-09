package io.github.zhengchalei.module.system.dto;

import javax.validation.constraints.Email;
import javax.ws.rs.QueryParam;
import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author <a href="mailto:stone981023@gmail.com">zhengchalei</a>
 * @since 1.0.0
 **/
public class SysUserDto implements Serializable {
    @QueryParam("id")
    public Long id;
    @QueryParam("username")
    public String username;
    @Email
    @QueryParam("email")
    public String email;
    public SysDepartmentDto department;
    public Set<SysRoleDto> roles = new LinkedHashSet<>();

}
