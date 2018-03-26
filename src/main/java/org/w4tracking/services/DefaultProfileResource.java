package org.w4tracking.services;

import org.w4tracking.ProfileResource;
import org.w4tracking.ResourceUtils;
import org.w4tracking.models.UserModel;
import org.w4tracking.models.UserProvider;
import org.w4tracking.models.utils.ModelToRepresentation;
import org.w4tracking.representations.idm.DataRepresentation;
import org.w4tracking.representations.idm.ItemRepresentation;
import org.w4tracking.representations.idm.LinksRepresentation;
import org.w4tracking.representations.idm.UserAttributesRepresentation;
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
    public ItemRepresentation<UserAttributesRepresentation> getProfile() {
        Optional<UserModel> userModel = userProvider.getUserByIdentityId(securityContext.getIdentityId());
        return toItemRepresentation(userModel.orElseGet(this::firstLogin));
    }

    private UserModel firstLogin() {
        UserModel user = userProvider.addUser(securityContext.getUsername(), securityContext.getIdentityId(), securityContext.getIdentityProviderAlias());
        user.setEmail(securityContext.getEmail());
        user.setFullName(securityContext.getFullName());
        return user;
    }

    private ItemRepresentation<UserAttributesRepresentation> toItemRepresentation(UserModel user) {
        URI self = uriInfo.getBaseUriBuilder()
                .path(DefaultProfileResource.class)
                .path(DefaultProfileResource.class, "getProfile")
                .build();
        LinksRepresentation links = new LinksRepresentation.Builder().withSelf(self).build();
        UserAttributesRepresentation userAttributes = ModelToRepresentation.toRepresentation(user, true);
        DataRepresentation<UserAttributesRepresentation> data = ResourceUtils.buildData(user, userAttributes, links);
        return new ItemRepresentation.Builder<UserAttributesRepresentation>().withData(data).build();
    }

}
