package hu.pemik.dcs.restserver.endpoints;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

/**
 * @author pekmil
 */
@Path("string")
public class StringManipulationEndpoint {

    @GET
    @Path("reverse/{stringToReverse}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response reverse(@Context SecurityContext sc, @PathParam("stringToReverse") String stringToReverse) {
        String reversedString = new StringBuilder(stringToReverse).reverse().toString();
        return Response.ok(reversedString).build();
    }

}
