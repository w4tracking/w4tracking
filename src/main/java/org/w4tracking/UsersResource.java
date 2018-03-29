package org.w4tracking;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.w4tracking.representations.idm.UserRepresentation;
import org.w4tracking.representations.idm.UsersRepresentation;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Api(value = "User Resource", description = "User REST API", consumes = "application/json")
public interface UsersResource {

    @POST
    @Path("/")
    void createUser();

    @GET
    @Path("/")
    @ApiOperation(value = "List Users")
    UsersRepresentation getUsers(
            @QueryParam("userId") String userId,
            @QueryParam("username") String username,
            @QueryParam("filterText") String filterText,
            @QueryParam("offset") @DefaultValue("0") Integer offset,
            @QueryParam("limit") @DefaultValue("10") Integer limit
    );

    @GET
    @Path("/{userId}")
    @ApiOperation(value = "Get User")
    UserRepresentation getUser(@PathParam("userId") String userId);

    @PUT
    @Path("/{userId}")
    @ApiOperation(value = "Update User")
    void updateUser(@PathParam("userId") String userId, UserRepresentation rep);

    @DELETE
    @Path("/{userId}")
    @ApiOperation(value = "Delete User")
    void deleteUser(@PathParam("userId") String userId);

}
