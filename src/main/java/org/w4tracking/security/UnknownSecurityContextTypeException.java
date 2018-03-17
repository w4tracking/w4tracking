package org.w4tracking.security;

public class UnknownSecurityContextTypeException extends RuntimeException {

    public UnknownSecurityContextTypeException(String securityContextType) {
        super("Unknown security context type:" + securityContextType);
    }
}
