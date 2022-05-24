package io.github.zhengchalei.module.system.resource;

import io.github.zhengchalei.common.model.Page;
import io.github.zhengchalei.module.system.domain.SysPermission;
import io.github.zhengchalei.module.system.service.SysPermissionService;

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
@Path("/api/system/permission")
public class SysPermissionResource {

    private static final String PATH = "/api/system/permission";

    @Inject
    SysPermissionService service;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<SysPermission> page(@BeanParam Page page, @BeanParam SysPermission sysPermission) {
        return service.findPage(page.build(), sysPermission);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/search/tree")
    public List<SysPermission> tree() {
        return service.tree();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/search/list")
    public List<SysPermission> list(@BeanParam SysPermission sysPermission) {
        return service.findAll(sysPermission);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public SysPermission findById(@PathParam("id") Long id) {
        return service.findById(id);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response save(@Valid SysPermission sysPermission) {
        service.save(sysPermission);
        return Response.created(URI.create(PATH + sysPermission.id)).build();
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response update(@Valid SysPermission sysPermission, @PathParam("id") Long id) {
        service.update(id, sysPermission);
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
