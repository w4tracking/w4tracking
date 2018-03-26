package org.w4tracking.services.uma;

import org.keycloak.KeycloakSecurityContext;
import org.keycloak.authorization.client.AuthzClient;
import org.keycloak.authorization.client.ClientAuthorizationContext;
import org.keycloak.authorization.client.representation.ResourceRepresentation;
import org.keycloak.authorization.client.representation.ScopeRepresentation;
import org.w4tracking.CompanyResource;
import org.w4tracking.representations.idm.CollectionRepresentation;
import org.w4tracking.representations.idm.CompanyAttributesRepresentation;
import org.w4tracking.representations.idm.DataRepresentation;
import org.w4tracking.representations.idm.ItemRepresentation;
import org.w4tracking.security.ISecurityContext;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.HashSet;

@Decorator
@ApplicationScoped
public class UMACompanyResource implements CompanyResource {

    public static final String SCOPE_COMPANY_VIEW = "urn:w4tracking.com:scopes:company:view";
    public static final String SCOPE_COMPANY_EDIT = "urn:w4tracking.com:scopes:company:edit";
    public static final String SCOPE_COMPANY_DELETE = "urn:w4tracking.com:scopes:company:delete";

    @Inject
    private ISecurityContext securityContext;

    @Inject
    @Delegate
    private CompanyResource delegate;

    private AuthzClient getAuthzClient() {
        return getAuthorizationContext().getClient();
    }

    private ClientAuthorizationContext getAuthorizationContext() {
        return ClientAuthorizationContext.class.cast(getKeycloakSecurityContext().getAuthorizationContext());
    }

    private KeycloakSecurityContext getKeycloakSecurityContext() {
        return KeycloakSecurityContext.class.cast(securityContext.getAttribute(KeycloakSecurityContext.class.getName()));
    }

    @Override
    public ItemRepresentation<CompanyAttributesRepresentation> createCompany(ItemRepresentation<CompanyAttributesRepresentation> rep) {
        ItemRepresentation<CompanyAttributesRepresentation> companyRepresentation = delegate.createCompany(rep);
        DataRepresentation<CompanyAttributesRepresentation> data = companyRepresentation.getData();
        CompanyAttributesRepresentation attributes = data.getAttributes();

        try {
            HashSet<ScopeRepresentation> scopes = new HashSet<>();

            scopes.add(new ScopeRepresentation(SCOPE_COMPANY_VIEW));
            scopes.add(new ScopeRepresentation(SCOPE_COMPANY_EDIT));
            scopes.add(new ScopeRepresentation(SCOPE_COMPANY_DELETE));

            ResourceRepresentation albumResource = new ResourceRepresentation(data.getId(), scopes, "/companies/" + data.getId(), "http://w4tracking.com/company");
            albumResource.setOwner(album.getUserId());

            getAuthzClient().protection().resource().create(albumResource);
        } catch (Exception e) {
            throw new RuntimeException("Could not register protected resource.", e);
        }

        return companyRepresentation;
    }

    @Override
    public void updateCompany(ItemRepresentation<CompanyAttributesRepresentation> rep) {
        delegate.updateCompany(rep);
    }

    @Override
    public CollectionRepresentation<CompanyAttributesRepresentation> getCompanies() {
        return delegate.getCompanies();
    }

    @Override
    public ItemRepresentation<CompanyAttributesRepresentation> getCompany(String companyId) {
        return delegate.getCompany(companyId);
    }
}
