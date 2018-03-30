package org.w4tracking.services;

import org.w4tracking.ProfileResource;
import org.w4tracking.models.ModelType;
import org.w4tracking.models.UserModel;
import org.w4tracking.models.UserProvider;
import org.w4tracking.models.utils.ModelToRepresentation;
import org.w4tracking.representations.idm.LinksRepresentation;
import org.w4tracking.representations.idm.UserRepresentation;
import org.w4tracking.security.ISecurityContext;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.Optional;

@Transactional
@ApplicationScoped
public class DefaultProfileResource implements ProfileResource {

    @Context
    private UriInfo uriInfo;

    @Inject
    private UserProvider userProvider;

    @Inject
    private ISecurityContext securityContext;

    @Override
    public UserRepresentation getProfile() {
        Optional<UserModel> userModel = userProvider.getUserByIdentityId(securityContext.getIdentityId());
        return toItemRepresentation(userModel.orElseGet(this::firstLogin));
    }

    private UserModel firstLogin() {
        UserModel user = userProvider.addUser(securityContext.getIdentityId(), securityContext.getUsername(), securityContext.getIdentityProviderAlias());
        user.setEmail(securityContext.getEmail());
        user.setFullName(securityContext.getFullName());
        return user;
    }

    private UserRepresentation toItemRepresentation(UserModel user) {
        URI self = uriInfo.getBaseUriBuilder()
                .path(ProfileResource.class)
                .path(ProfileResource.class, "getProfile")
                .build();
        LinksRepresentation links = new LinksRepresentation.Builder().withSelf(self).build();

        UserRepresentation.UserAttributesRepresentation userAttributes = ModelToRepresentation.toRepresentation(user, true);

        UserRepresentation.UserData data = new UserRepresentation.UserData();
        data.setId(user.getId());
        data.setType(ModelType.COMPANY.toString());
        data.setLinks(links);
        data.setAttributes(userAttributes);

        UserRepresentation userRepresentation = new UserRepresentation();
        userRepresentation.setData(data);
        return userRepresentation;
    }

}
