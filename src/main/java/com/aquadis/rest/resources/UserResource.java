package com.aquadis.rest.resources;

import com.aquadis.models.User;
import com.aquadis.rest.model.ClientError;
import com.aquadis.service.RepositoryService;
import com.aquadis.service.impl.RepositoryServiceImpl;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * @author Lorenzo
 */
@Path("/users")
public class UserResource {

    private RepositoryService service;

    public UserResource() {
        service = RepositoryServiceImpl.getInstance();
    }

    /**
     * Return a list of all the user objects.
     * at: http://localhost:8080/aquadis/rest/users
     *
     * @return user objects list
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> getAllUsers() {
        return service.getAllUsers();
    }

    /**
     * Returns a specific user object based on its ID.
     * at: http://localhost:8080/aquadis/rest/users/{userID}
     *
     * @param userID specific user
     * @return user object
     */
    @GET
    @Path("/{userID}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserFromID(@PathParam("userID") int userID) {
        User user = service.getUserFromId(userID);

        if (user == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(new ClientError("Cannot find user with id: " + userID)).build();
        }

        return Response.status(Response.Status.OK)
                .entity(user).build();
    }

    /**
     * @param email
     * @param password
     * @return
     */
    @GET
    @Path("/user")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsers(
            @QueryParam("email") String email,
            @QueryParam("password") String password) {

        User user = service.getUserFromloginFields(email, password);

        if (user == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(new ClientError("Cannot find user with email: " + email + "and password: " + password)).build();
        }

        return Response.status(Response.Status.OK)
                .entity(user).build();

    }

    /**
     * Adds a user to the database
     *
     * @param user specific user
     * @return added user
     */
    @POST
    @Path("/user")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public User addUser(User user) {
        return service.addUser(user);
    }

    /**
     * Returns a group resource
     * at: http://localhost:8080/aquadis/rest/users/{userID}/groups
     *
     * @return a new group resource
     */
    @Path("/{userID}/ug")
    public UserGroupResource getUserGroupResource() {
        return new UserGroupResource();
    }
}
