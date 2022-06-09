package io.github.zhengchalei.module.auth.resource;

import io.github.zhengchalei.common.AuthUtil;
import io.github.zhengchalei.module.auth.dto.LoginDTO;
import io.github.zhengchalei.module.system.domain.SysUser;
import io.quarkus.arc.impl.Sets;
import io.quarkus.panache.common.Parameters;
import io.quarkus.redis.client.RedisClient;
import io.smallrye.jwt.build.Jwt;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.jwt.Claims;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.time.Duration;
import java.util.List;
import java.util.Map;

/**
 * @author <a href="mailto:stone981023@gmail.com">zhengchalei</a>
 **/
@Path("/api/auth/login")
public class SysAuthResource {

    @ConfigProperty(name = "mp.jwt.verify.issuer")
    String issuer;

    @Inject
    RedisClient redisClient;

    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("username")
    @POST()
    public Map<String, String> login(@Valid LoginDTO loginDto) {
        SysUser user = SysUser.<SysUser>find(
                        "username = :username",
                        Parameters.with("username", loginDto.username)
                ).firstResultOptional()
                .orElseThrow(() -> new NotFoundException("用户名不存在"));
        if (!user.password.equals(loginDto.password)) {
            throw new RuntimeException("密码不正确!");
        }
        // 这里会拿到配置文件的 key 生成 jwt
        String token = Jwt.issuer(issuer)
                .upn(user.email)
                .groups(Sets.of("User", "ADMIN"))
                .claim(Claims.birthdate, "1998-10-23")
                .expiresIn(Duration.ofHours(6))
                .sign();
        redisClient.set(List.of(AuthUtil.AUTH_KEY + user.getId(), token));
        return Map.of("token", token);
    }

}
