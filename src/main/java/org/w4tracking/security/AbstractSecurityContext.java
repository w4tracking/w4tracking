package org.w4tracking.security;

import javax.servlet.http.HttpServletRequest;

public abstract class AbstractSecurityContext implements ISecurityContext {

    public static final ThreadLocal<HttpServletRequest> servletRequest = new ThreadLocal<>();

    protected static void setServletRequest(HttpServletRequest request) {
        servletRequest.set(request);
    }

    protected static void clearServletRequest() {
        servletRequest.remove();
    }

}
