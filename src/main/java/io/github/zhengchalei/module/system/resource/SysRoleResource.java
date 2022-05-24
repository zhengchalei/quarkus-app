package io.github.zhengchalei.module.system.resource;

import io.github.zhengchalei.common.model.Page;
import io.github.zhengchalei.module.system.domain.SysRole;
import io.github.zhengchalei.module.system.service.SysRoleService;

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
@Path("/api/system/role")
public class SysRoleResource {

    private static final String PATH = "/api/system/role";

    @Inject
    SysRoleService service;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<SysRole> page(@BeanParam Page page, @BeanParam SysRole sysRole) {
        return service.findPage(page.build(), sysRole);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/search/list")
    public List<SysRole> list(@BeanParam SysRole sysRole) {
        return service.findAll(sysRole);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public SysRole findById(@PathParam("id") Long id) {
        return service.findById(id);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response save(@Valid SysRole sysRole) {
        service.save(sysRole);
        return Response.created(URI.create(PATH + sysRole.id)).build();
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@Valid SysRole sysRole) {
        service.update(sysRole);
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
