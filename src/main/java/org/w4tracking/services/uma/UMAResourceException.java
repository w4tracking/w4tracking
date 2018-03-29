package org.w4tracking.services.uma;

public class UMAResourceException extends RuntimeException {
    public UMAResourceException(String message, Exception e) {
        super(message, e);
    }
}
