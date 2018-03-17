package org.w4tracking.security;

import org.wildfly.swarm.spi.runtime.annotations.ConfigurationValue;

import javax.ejb.Stateless;
import javax.enterprise.inject.Produces;
import java.util.Optional;

@Stateless
public class SecurityContextFactory {

    @Produces
    public static SecurityContext provideSecurityContext(
            @ConfigurationValue("w4tracking.security-context.type") Optional<String> sc,
            @SecurityContextType(name = SecurityContextType.IdentityProvider.DEFAULT) SecurityContext defaultSC,
            @SecurityContextType(name = SecurityContextType.IdentityProvider.KEYCLOAK) SecurityContext keycloakSC) {
        String securityContext = sc.orElse("default");
        if ("default".equals(securityContext)) {
            return defaultSC;
        } else if ("keycloak".equals(securityContext)) {
            return keycloakSC;
        } else {
            throw new UnknownSecurityContextTypeException(securityContext);
        }
    }

}
