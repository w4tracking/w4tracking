package org.w4tracking.config;

import org.w4tracking.security.DefaultSecurityContext;
import org.w4tracking.security.KeycloakSecurityContext;
import org.w4tracking.security.SecurityContext;
import org.w4tracking.security.SecurityProvider;
import org.wildfly.swarm.spi.runtime.annotations.ConfigurationValue;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import java.util.Optional;

public class ClarksnutCdiFactory {

    @Produces
    @ApplicationScoped
    public static SecurityContext provideSecurityContext(@ConfigurationValue("w4tracking.security-context.type") Optional<String> sc,
                                                         @SecurityProvider(name = SecurityProvider.Provider.DEFAULT) DefaultSecurityContext defaultSC,
                                                         @SecurityProvider(name = SecurityProvider.Provider.KEYCLOAK) KeycloakSecurityContext keycloakSC) {
        String defaultSc = sc.orElse("default");
        if ("default".equals(defaultSc)) {
            return defaultSC;
        } else if ("keycloak".equals(defaultSc)) {
            return keycloakSC;
        } else {
            throw new RuntimeException("Unknown security context type: " + sc);
        }
    }

}
