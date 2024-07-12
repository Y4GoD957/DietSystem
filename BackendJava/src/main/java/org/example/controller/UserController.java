package org.example.controller;

import org.example.entity.User;
import org.example.service.UserService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.PathParam;
import java.util.List;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserController {

    private final UserService userService = new UserService();

    @POST
    public Response createUser(User u) {
        userService.createUser(u);
        return Response.status(Response.Status.CREATED).entity("Usuário criado com sucesso").build();
    }

    @GET
    @Path("/{userId}")
    public Response getUser(@PathParam("userId") int userId) {
        User u = userService.readUser(userId);
        if (u != null) {
            return Response.ok(u).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Usuário não encontrado").build();
        }
    }

    @GET
    public Response getAllUsers() {
        List<User> users = userService.readAllUsers();
        return Response.ok(users).build();
    }

    @PUT
    @Path("/{userId}")
    public Response updateUser(@PathParam("userId") int userId, User u) {
        u.setUser_id(userId);
        userService.updateUser(u);
        return Response.ok("Usuário atualizado com sucesso").build();
    }

    @DELETE
    @Path("/{userId}")
    public Response deleteUser(@PathParam("userId") int userId) {
        userService.deleteUser(userId);
        return Response.ok("Usuário deletado com sucesso").build();
    }
}
