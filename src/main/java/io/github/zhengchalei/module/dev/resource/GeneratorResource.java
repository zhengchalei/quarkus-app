package io.github.zhengchalei.module.dev.resource;

import io.github.zhengchalei.common.Util;
import io.github.zhengchalei.module.dev.domain.GenMetaData;
import io.github.zhengchalei.module.dev.service.GeneratorService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * @author <a href="mailto:stone981023@gmail.com">zhengchalei</a>
 **/
@Path("/api/dev")
public class GeneratorResource {

    @Inject
    GeneratorService generatorService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{class}")
    public String genOne(@BeanParam GenMetaData metaData) {
        GenMetaData genMetaData = buildMetaData(metaData);
        generatorFile(genMetaData);
        return Util.toJsonStr(genMetaData);
    }

    @POST
    @Path("/gen/list")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List<String> genList(List<GenMetaData> list) {
        return list.stream().map(this::buildMetaData)
                .peek(this::generatorFile)
                .map(Util::toJsonStr)
                .toList();
    }

    private GenMetaData buildMetaData(GenMetaData item) {
        try {
            Class<?> aClass = Class.forName(item.classzz);
            return item.build(aClass);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void generatorFile(GenMetaData metaData) {
        generatorService.service(metaData);
        generatorService.serviceImpl(metaData);
        generatorService.resource(metaData);
    }

}
