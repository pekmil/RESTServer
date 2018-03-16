package hu.pemik.dcs.restserver.endpoints;

import hu.pemik.dcs.restserver.model.entity.Todo;
import hu.pemik.dcs.restserver.service.TodoService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@Path("todos")
public class TodoEndpoints {

    @Inject
    private TodoService service;

    @GET
    @Path("all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll(@Context SecurityContext sc) {
        return Response.ok(this.service.findAll(sc.getUserPrincipal().getName())).build();
    }

    @GET
    @Path("todo/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@Context SecurityContext sc, @PathParam("id") int id) {
        return Response.ok(this.service.find(id)).build();
    }

    @POST
    @Path("todo")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createTodo(@Context SecurityContext sc, Todo todo) {
        this.service.create(todo, sc.getUserPrincipal().getName());
        WebSocketNotificationEndpoint.sendNotification("Todo created!");
        return Response.ok().status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("todo")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateTodo(@Context SecurityContext sc, Todo todo) {
        this.service.edit(todo, sc.getUserPrincipal().getName());
        WebSocketNotificationEndpoint.sendNotification("Todo updated!");
        return Response.ok().build();
    }

    @DELETE
    @Path("todo/{id}")
    public Response deleteTodo(@Context SecurityContext sc, @PathParam("id") int id) {
        this.service.remove(id);
        WebSocketNotificationEndpoint.sendNotification("Todo deleted!");
        return Response.ok().build();
    }

}
