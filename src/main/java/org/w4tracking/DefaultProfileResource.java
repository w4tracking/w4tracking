package org.w4tracking;

import org.w4tracking.models.UserModel;
import org.w4tracking.models.UserProvider;
import org.w4tracking.models.utils.ModelToRepresentation;
import org.w4tracking.representations.idm.*;
import org.w4tracking.security.SecurityContext;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;

@Stateless
public class DefaultProfileResource implements ProfileResource {

    @Context
    private UriInfo uriInfo;

    @Inject
    private SecurityContext securityContext;

    @Inject
    private UserProvider userProvider;

    @Override
    public ItemRepresentation<UserAttributesRepresentation> getProfile() {
        String username = securityContext.getUsername();
        String identityId = securityContext.getIdentityId();
        String identityProvider = securityContext.getIdentityProviderAlias();

        UserModel user = userProvider.getUserByIdentityId(identityId);
        if (user == null) {
            user = userProvider.addUser(username, identityId, identityProvider);
            user.setEmail(securityContext.getEmail());
            user.setFullName(securityContext.getFullName());
        }

        URI self = uriInfo.getBaseUriBuilder()
                .path(ProfileResource.class, "getProfile")
                .build();
        LinksRepresentation links = new LinksRepresentation.Builder().withSelf(self).build();
        UserAttributesRepresentation userAttributes = ModelToRepresentation.toRepresentation(user, true);
        DataRepresentation<UserAttributesRepresentation> data = ResourceUtils.buildData(user, userAttributes, links);
        return new ItemRepresentation.Builder<UserAttributesRepresentation>().withData(data).build();
    }

}
