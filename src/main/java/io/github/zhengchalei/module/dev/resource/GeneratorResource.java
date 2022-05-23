package io.github.zhengchalei.module.dev.resource;

import io.github.zhengchalei.module.dev.domain.GeneratorMetaData;
import io.github.zhengchalei.module.system.domain.SysRole;
import io.quarkus.qute.CheckedTemplate;
import io.quarkus.qute.TemplateInstance;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * @author <a href="mailto:stone981023@gmail.com">zhengchalei</a>
 **/
@Path("/api/dev")
public class GeneratorResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String resource() {
        GeneratorMetaData generatorMetaData = new GeneratorMetaData(false, SysRole.class);
        return Templates.resource(generatorMetaData).render();
    }

    @CheckedTemplate(requireTypeSafeExpressions = false)
    public static class Templates {
        public static native TemplateInstance resource(GeneratorMetaData data);
    }

}
