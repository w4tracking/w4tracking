package org.w4tracking.security;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;
import javax.servlet.http.HttpServletRequest;

@ApplicationScoped
@SecurityProvider(name = SecurityProvider.Provider.DEFAULT)
public class DefaultSecurityContext implements SecurityContext {

    public static final ThreadLocal<HttpServletRequest> servletRequest = new ThreadLocal<>();

    @Override
    public String getUsername() {
        return servletRequest.get().getRemoteUser();
    }

    @Override
    public String getFullName() {
        return null;
    }

    @Override
    public String getEmail() {
        return null;
    }

    @Override
    public String getRequestHeader(String headerName) {
        return servletRequest.get().getHeader(headerName);
    }

    @Override
    public String getIdentityId() {
        return servletRequest.get().getRemoteUser();
    }

    @Override
    public String getIdentityProviderAlias() {
        return "default";
    }

    protected static void setServletRequest(HttpServletRequest request) {
        servletRequest.set(request);
    }

    protected static void clearServletRequest() {
        servletRequest.remove();
    }

}
