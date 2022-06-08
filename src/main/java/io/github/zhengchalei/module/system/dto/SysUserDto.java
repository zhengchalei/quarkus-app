package io.github.zhengchalei.module.system.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.ws.rs.QueryParam;
import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author <a href="mailto:stone981023@gmail.com">zhengchalei</a>
 * @since 1.0.0
 **/
@Data
public class SysUserDto implements Serializable {
    @QueryParam("id")
    private Long id;
    @QueryParam("username")
    private String username;
    @Email
    @QueryParam("email")
    private String email;
    private SysDepartmentDto department;
    private Set<SysRoleDto> roles = new LinkedHashSet<>();
}
