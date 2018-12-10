package com.aquadis.rest.resources;

import com.aquadis.models.Group;
import com.aquadis.models.User;
import com.aquadis.models.UserGroup;
import com.aquadis.rest.model.ClientError;
import com.aquadis.service.RepositoryService;
import com.aquadis.service.impl.RepositoryServiceImpl;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Comparator;
import java.util.List;

/**
 * @author Lorenzo
 */
@Path("/")
public class UserGroupResource {
    private RepositoryService service;

    public UserGroupResource() {
        service = RepositoryServiceImpl.getInstance();
    }

    @GET
    @Path("/{ID}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<UserGroup> getAllUserGroups(@PathParam("ID") int ID) {
        return service.getAllUserGroups(ID);
    }

    @POST
    @Path("/usergroup")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public UserGroup addUserGroup(UserGroup userGroup){
        return service.addUserGroup(userGroup);
    }

    @GET
    @Path("/groups")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getGroupsFromUser(@PathParam("userID") int userID) {
        User user = service.getUserFromId(userID);

        if (user == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(new ClientError("Cannot find user with id: " + userID)).build();
        }

        List<UserGroup> userGroups = service.getAllUserGroupsFromUser(userID);

        return Response.status(Response.Status.OK).entity(userGroups).build();
    }

    @GET
    @Path("/groups/{groupID}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getGroupFromUser(@PathParam("userID") int userID, @PathParam("groupID") int groupID) {
        User user = service.getUserFromId(userID);

        if (user == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(new ClientError("Cannot find user with id: " + userID)).build();
        }

        Group group = service.getGroupFromId(groupID);

        if (group == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(new ClientError("Cannot find group with id: " + groupID)).build();
        }

        return Response.status(Response.Status.OK).entity(group).build();
    }

    @GET
    @Path("/groups/{groupID}/users")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsersFromGroup(@PathParam("userID") int userID, @PathParam("groupID") int groupID) {
        User user = service.getUserFromId(userID);

        if (user == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(new ClientError("Cannot find user with id: " + userID)).build();
        }

        Group group = service.getGroupFromId(groupID);

        if (group == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(new ClientError("Cannot find group with id: " + groupID)).build();
        }

        List<UserGroup> users = group.getUsers();
        users.sort((o1, o2) -> o2.getPoints() - o1.getPoints());

        return Response.status(Response.Status.OK).entity(users).build();
    }

    @GET
    @Path("/users")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getGroupsFromGroup(@PathParam("groupID") int groupID) {
        Group group = service.getGroupFromId(groupID);

        if (group == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(new ClientError("Cannot find group with id: " + groupID)).build();
        }

        List<UserGroup> userGroups = service.getAllUserGroupsFromGroup(groupID);

        return Response.status(Response.Status.OK).entity(userGroups).build();
    }
}
