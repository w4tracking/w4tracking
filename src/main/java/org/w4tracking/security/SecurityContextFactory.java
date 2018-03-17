package org.w4tracking.security;

import org.wildfly.swarm.spi.runtime.annotations.ConfigurationValue;

import javax.ejb.Stateless;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import java.util.Optional;

@Stateless
public class SecurityContextFactory {

    @Inject
    @ConfigurationValue("w4tracking.security-context.type")
    Optional<String> securityContext;

    @Inject
    @SecurityContextType(name = SecurityContextType.IdentityProvider.DEFAULT)
    SecurityContext defaultSecurityContext;

    @Inject
    @SecurityContextType(name = SecurityContextType.IdentityProvider.KEYCLOAK)
    SecurityContext keycloakSecurityContext;

    @Produces
    public SecurityContext provideSecurityContext() {
        String selectedSecurityContext = securityContext.orElse("default");
        if ("default".equals(selectedSecurityContext)) {
            return defaultSecurityContext;
        } else if ("keycloak".equals(selectedSecurityContext)) {
            return keycloakSecurityContext;
        } else {
            throw new UnknownSecurityContextTypeException(selectedSecurityContext);
        }
    }

}
