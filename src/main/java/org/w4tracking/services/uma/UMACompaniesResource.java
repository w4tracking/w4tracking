package org.w4tracking.services.uma;

import org.keycloak.KeycloakSecurityContext;
import org.keycloak.authorization.client.AuthzClient;
import org.keycloak.authorization.client.ClientAuthorizationContext;
import org.keycloak.authorization.client.representation.ResourceRepresentation;
import org.keycloak.authorization.client.representation.ScopeRepresentation;
import org.w4tracking.CompaniesResource;
import org.w4tracking.representations.idm.CompanyRepresentation;
import org.w4tracking.security.ISecurityContext;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.util.HashSet;

@Decorator
@Dependent
public abstract class UMACompaniesResource implements CompaniesResource {

    public static final String SCOPE_COMPANY_VIEW = "urn:w4tracking.com:scopes:company:view";
    public static final String SCOPE_COMPANY_EDIT = "urn:w4tracking.com:scopes:company:edit";
    public static final String SCOPE_COMPANY_DELETE = "urn:w4tracking.com:scopes:company:delete";

    @Inject
    private ISecurityContext securityContext;

    @Inject
    @Delegate
    private CompaniesResource delegate;

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
    public CompanyRepresentation createCompany(CompanyRepresentation rep) {
        CompanyRepresentation company = delegate.createCompany(rep);
        CompanyRepresentation.CompanyData data = company.getData();
        CompanyRepresentation.CompanyOwnedBy ownedBy = data.getRelationships().getOwnedBy();

        try {
            HashSet<ScopeRepresentation> scopes = new HashSet<>();

            scopes.add(new ScopeRepresentation(SCOPE_COMPANY_VIEW));
            scopes.add(new ScopeRepresentation(SCOPE_COMPANY_EDIT));
            scopes.add(new ScopeRepresentation(SCOPE_COMPANY_DELETE));

            ResourceRepresentation albumResource = new ResourceRepresentation("Company[" + data.getId() + "]", scopes, "/companies/" + data.getId(), "http://w4tracking.com/companies");
            albumResource.setOwner(ownedBy.getData().getId());

            getAuthzClient().protection().resource().create(albumResource);
        } catch (Exception e) {
            throw new UMAResourceException("Could not register protected resource.", e);
        }

        return company;
    }

}
