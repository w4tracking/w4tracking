package org.w4tracking.security;

import javax.ejb.Stateless;
import javax.enterprise.inject.Typed;
import javax.servlet.http.HttpServletRequest;

@Stateless
@SecurityContextType(name = SecurityContextType.IdentityProvider.DEFAULT)
public class DefaultSecurityContext implements SecurityContext {

    public static final ThreadLocal<HttpServletRequest> servletRequest = new ThreadLocal<>();

    @Override
    public String getUsername() {
        return servletRequest.get().getRemoteUser();
    }

    @Override
    public String getFullName() {
        // No data to extract from
        return null;
    }

    @Override
    public String getEmail() {
        // No data to extract from
        return null;
    }

    @Override
    public String getIdentityId() {
        return servletRequest.get().getRemoteUser();
    }

    @Override
    public String getIdentityProviderAlias() {
        return "default";
    }

    @Override
    public String getRequestHeader(String headerName) {
        return servletRequest.get().getHeader(headerName);
    }

    protected static void setServletRequest(HttpServletRequest request) {
        servletRequest.set(request);
    }

    protected static void clearServletRequest() {
        servletRequest.remove();
    }

}
