package org.w4tracking.services.resources.models.transaction;

public interface W4TrackingTransaction {

    void begin();

    void commit();

    void rollback();

    void setRollbackOnly();

    boolean isActive();
}
