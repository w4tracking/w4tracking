package org.w4tracking.services;

import org.w4tracking.UsersResource;
import org.w4tracking.models.ModelType;
import org.w4tracking.models.UserModel;
import org.w4tracking.models.UserProvider;
import org.w4tracking.models.utils.ModelToRepresentation;
import org.w4tracking.representations.idm.LinksRepresentation;
import org.w4tracking.representations.idm.UserRepresentation;
import org.w4tracking.representations.idm.UsersRepresentation;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Transactional
@ApplicationScoped
public class DefaultUsersResource implements UsersResource {

    @Context
    private UriInfo uriInfo;

    @Inject
    private UserProvider userProvider;

    @POST
    @Path("/")
    public void createUser() {
        throw new ForbiddenException();
    }

    private UserRepresentation.UserData toData(UserModel model, UserRepresentation.UserAttributesRepresentation attributes) {
        URI self = uriInfo
                .getAbsolutePathBuilder()
                .path(model.getId())
                .build();
        LinksRepresentation links = new LinksRepresentation();
        links.setSelf(self.toString());

        UserRepresentation.UserData data = new UserRepresentation.UserData();
        data.setId(model.getId());
        data.setType(ModelType.USER.getAlias());
        data.setLinks(links);
        data.setAttributes(attributes);
        return data;
    }

    @Override
    public UsersRepresentation getUsers(
            String userId,
            String username,
            String filterText,
            Integer offset,
            Integer limit
    ) {
        if (userId != null || username != null) {
            Optional<UserModel> userModel;
            if (userId != null) {
                userModel = userProvider.getUser(userId);
            } else {
                userModel = userProvider.getUserByUsername(username);
            }
            return userModel
                    .map(model -> toData(model, ModelToRepresentation.toRepresentation(model, false)))
                    .map(Collections::singletonList)
                    .map(UsersRepresentation::new)
                    .orElseGet(() -> new UsersRepresentation(Collections.emptyList()));
        } else if (filterText != null) {
            List<UserRepresentation.UserData> data = userProvider.getUsers(filterText, offset, limit)
                    .stream()
                    .map(model -> toData(model, ModelToRepresentation.toRepresentation(model, false)))
                    .collect(Collectors.toList());
            return new UsersRepresentation(data);
        } else {
            throw new BadRequestException("You need to pass valid parameters");
        }
    }

    @Override
    public UserRepresentation getUser(String userId) {
        UserModel userModel = userProvider.getUser(userId).orElseThrow(NotFoundException::new);
        UserRepresentation.UserData data = toData(userModel, ModelToRepresentation.toRepresentation(userModel, true));
        return new UserRepresentation(data);
    }

    @Override
    public void updateUser(String userId, UserRepresentation rep) {
        userProvider.getUser(userId).orElseThrow(NotFoundException::new);
    }

    @Override
    public void deleteUser(String userId) {
        throw new ForbiddenException();
    }

}
