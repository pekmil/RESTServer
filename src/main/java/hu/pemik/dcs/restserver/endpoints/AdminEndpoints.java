package hu.pemik.dcs.restserver.endpoints;

import hu.pemik.dcs.restserver.service.TodoService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * @author pekmil
 */
@Path("admin")
public class AdminEndpoints {

    @Inject
    private TodoService service;

    @GET
    @Path("ping")
    @Produces(MediaType.TEXT_PLAIN)
    public Response pingService() {
        return Response.ok("pong").build();
    }

}
