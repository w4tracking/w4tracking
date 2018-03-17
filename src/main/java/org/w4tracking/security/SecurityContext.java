package org.w4tracking.security;

public interface SecurityContext {

    String getUsername();
    String getFullName();
    String getEmail();
    String getRequestHeader(String headerName);

    String getIdentityId();
    String getIdentityProviderAlias();
}
