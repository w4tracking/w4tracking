package org.w4tracking.security;

import javax.inject.Qualifier;
import java.lang.annotation.*;

@Qualifier
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER, ElementType.TYPE})
@Documented
public @interface SecurityContextType {

    IdentityProvider name();

    enum IdentityProvider {
        KEYCLOAK
    }
}