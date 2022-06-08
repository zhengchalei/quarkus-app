package io.github.zhengchalei.module.system.resource;

import io.github.zhengchalei.common.model.Page;
import io.github.zhengchalei.module.system.dto.SysUserDto;
import io.github.zhengchalei.module.system.service.SysUserService;
import io.quarkus.security.Authenticated;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;

/**
 * @author <a href="mailto:stone981023@gmail.com">zhengchalei</a>
 **/
@Authenticated
@Path("/api/system/user")
public class SysUserResource {

    private static final String PATH = "/api/system/user";

    @Inject
    SysUserService service;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<SysUserDto> page(@BeanParam Page page, @BeanParam SysUserDto sysUserDto) {
        return service.findPage(page.build(), sysUserDto);
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/search/list")
    public List<SysUserDto> list(@BeanParam SysUserDto sysUserDto) {
        return service.findList(sysUserDto);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public SysUserDto findById(@PathParam("id") Long id) {
        return service.findById(id);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response save(@Valid SysUserDto sysUserDto) {
        service.save(sysUserDto);
        return Response.created(URI.create(PATH + sysUserDto.getId())).build();
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response update(@Valid SysUserDto sysUserDto, @PathParam("id") Long id) {
        service.update(id, sysUserDto);
        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        if (service.delete(id)) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok().build();
    }
}
