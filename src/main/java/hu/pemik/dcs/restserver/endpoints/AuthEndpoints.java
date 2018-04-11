package hu.pemik.dcs.restserver.endpoints;

import hu.pemik.dcs.restserver.database.Database;
import hu.pemik.dcs.restserver.models.User;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@Path("auth")
public class AuthEndpoints {

    @GET
    @Path("login")
    @Produces(MediaType.APPLICATION_JSON)
    public Response index(@Context SecurityContext sc) throws Exception {
        String email = sc.getUserPrincipal().getName();
        User user = Database.query().users.where("email", email).get();

        return Response.ok(user).build();
    }

}