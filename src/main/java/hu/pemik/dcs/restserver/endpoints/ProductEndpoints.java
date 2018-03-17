package hu.pemik.dcs.restserver.endpoints;

import hu.pemik.dcs.restserver.database.Database;
import hu.pemik.dcs.restserver.models.Product;
import hu.pemik.dcs.restserver.models.Warehouse;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@Path("products")
public class ProductEndpoints {

    @GET
    @Path("all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response index(@Context SecurityContext sc) {
        return Response.ok(Database.query().products).build();
    }

    @POST
    @Path("product")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response store(@Context SecurityContext sc, Product product) {
        Database db = Database.query();
        Warehouse warehouse = db.warehouse;

        try {
            db.dump();

            warehouse.storeProduct(product);
            db.products.insert(product);
            db.save();

            db.dump();
        } catch (Exception e) {
            throw new BadRequestException("Couldn't save product.");
        }

        return Response.ok().status(Response.Status.OK).build();
    }

}