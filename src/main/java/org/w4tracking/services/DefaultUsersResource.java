package org.w4tracking.services;

import org.w4tracking.UsersResource;
import org.w4tracking.models.UserProvider;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Collections;
import java.util.List;

@Transactional
@ApplicationScoped
public class DefaultUsersResource implements UsersResource {

    @Inject
    private UserProvider userProvider;

    @POST
    @Path("/")
    public void createUser() {
        throw new ForbiddenException();
    }

    @GET
    @Path("/")
    public List<UserRepresentation> getUsers(
            @QueryParam("userId") String userId,
            @QueryParam("username") String username
    ) {
        if (userId != null) {
            return userProvider.getUser(userId)
                    .map(model -> Collections.singletonList(ModelToRepresentation.toRepresentation(model, false)))
                    .orElseGet(Collections::emptyList);
        }
        if (username != null) {
            return userProvider.getUserByUsername(username)
                    .map(model -> Collections.singletonList(ModelToRepresentation.toRepresentation(model, false)))
                    .orElseGet(Collections::emptyList);
        } else {
            throw new IllegalStateException("Unimplemented");
        }
    }

    @GET
    @Path("/{userId}")
    public UserRepresentation getUser(@PathParam("userId") String userId) {
        UserModel userModel = userProvider.getUser(userId).orElseThrow(NotFoundException::new);
        return ModelToRepresentation.toRepresentation(userModel, true);
    }

    @PUT
    @Path("/{userId}")
    public UserRepresentation updateUser(@PathParam("userId") String userId, UserRepresentation rep) {
        UserModel userModel = userProvider.getUser(userId).orElseThrow(NotFoundException::new);
        return ModelToRepresentation.toRepresentation(userModel, true);
    }

    @DELETE
    @Path("/{userId}")
    public void deleteUser(@PathParam("userId") String userId) {
        throw new ForbiddenException();
    }

}
