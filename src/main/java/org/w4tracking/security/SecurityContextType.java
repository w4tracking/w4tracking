package org.w4tracking.security;

import javax.enterprise.util.Nonbinding;
import javax.inject.Qualifier;
import java.lang.annotation.*;

@Qualifier
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER, ElementType.TYPE})
@Documented
public @interface SecurityContextType {

    @Nonbinding IdentityProvider name();

    enum IdentityProvider {
        DEFAULT,
        KEYCLOAK
    }
}