package org.w4tracking.models;

public enum ModelType {
    USER("user"),
    COMPANY("company");

    private String alias;

    ModelType(String alias) {
        this.alias = alias;
    }

    public String getAlias() {
        return alias;
    }
}
