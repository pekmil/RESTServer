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
public class StringManipulationEndpoints {

    @GET
    @Path("reverse/{stringToReverse}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response reverse(@Context SecurityContext sc, @PathParam("stringToReverse") String stringToReverse) {
        System.out.println("Try to reverse string:" + stringToReverse);
        String reversedString = new StringBuilder(stringToReverse).reverse().toString();
        System.out.println("Reversed string:" + reversedString);
        return Response.ok(reversedString).build();
    }

}
