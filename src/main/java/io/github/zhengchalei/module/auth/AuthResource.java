package io.github.zhengchalei.module.auth;

import io.github.zhengchalei.module.auth.dto.LoginDTO;
import io.quarkus.arc.impl.Sets;
import io.smallrye.jwt.build.Jwt;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.jwt.Claims;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.time.Duration;

/**
 * @author <a href="mailto:stone981023@gmail.com">zhengchalei</a>
 **/
@Path("/api/login")
public class AuthResource {

    @ConfigProperty(name = "mp.jwt.verify.issuer")
    String issuer;

    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    @POST
    public String login(@Valid LoginDTO loginDto) {
        // 这里会拿到配置文件的 key 生成 jwt
        return Jwt.issuer(issuer)
                .upn("stone981023@gmail.com")
                .groups(Sets.of("User", "ADMIN"))
                .claim(Claims.birthdate, "1998-10-23")
                .expiresIn(Duration.ofHours(6))
                .sign();
    }

}
