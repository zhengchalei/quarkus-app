package io.github.zhengchalei.module.dev.resource;

import io.github.zhengchalei.common.Util;
import io.github.zhengchalei.module.dev.domain.GeneratorMetaData;
import io.github.zhengchalei.module.dev.service.GeneratorService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * @author <a href="mailto:stone981023@gmail.com">zhengchalei</a>
 **/
@Path("/api/dev")
public class GeneratorResource {

    @Inject
    GeneratorService generatorService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String resource(
            @QueryParam("class") String classs
    ) throws ClassNotFoundException {
        GeneratorMetaData generatorMetaData = new GeneratorMetaData(false, Class.forName(classs));
        generatorFile(generatorMetaData);
        return Util.toJsonStr(generatorMetaData);
    }

    private void generatorFile(GeneratorMetaData metaData) {
        generatorService.service(metaData);
        generatorService.serviceImpl(metaData);
        generatorService.resource(metaData);
    }


}
