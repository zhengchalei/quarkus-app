package io.github.zhengchalei.module.system.resource;

import io.github.zhengchalei.common.model.Page;
import io.github.zhengchalei.module.system.domain.SysDepartment;
import io.github.zhengchalei.module.system.service.SysDepartmentService;
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
@Path("/api/system/department")
public class SysDepartmentResource {

    private static final String PATH = "/api/system/department";

    @Inject
    SysDepartmentService service;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<SysDepartment> page(@BeanParam Page page, @BeanParam SysDepartment sysDepartment) {
        return service.findPage(page.build(), sysDepartment);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/search/tree")
    public List<SysDepartment> tree() {
        return service.tree();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/search/list")
    public List<SysDepartment> list(@BeanParam SysDepartment sysDepartment) {
        return service.findAll(sysDepartment);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public SysDepartment findById(@PathParam("id") Long id) {
        return service.findById(id);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response save(@Valid SysDepartment sysDepartment) {
        service.save(sysDepartment);
        return Response.created(URI.create(PATH + sysDepartment.getId())).build();
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response update(@Valid SysDepartment sysDepartment, @PathParam("id") Long id) {
        service.update(id, sysDepartment);
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
