package org.w4tracking.representations.idm;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class Representation<T> {

    private T data;
    private Map<String, Object> meta;
    private Map<String, String> links;

    public Representation(Builder<T> builder) {
        this.data = builder.data;
        this.meta  = builder.meta;
        this.links = builder.links;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Map<String, Object> getMeta() {
        return meta;
    }

    public void setMeta(Map<String, Object> meta) {
        this.meta = meta;
    }

    public Map<String, String> getLinks() {
        return links;
    }

    public void setLinks(Map<String, String> links) {
        this.links = links;
    }

    public static class Builder<U> implements org.w4tracking.common.Builder<Representation<U>> {

        private U data;
        private Map<String, Object> meta  = new HashMap<>();
        private Map<String, String> links  = new HashMap<>();

        @Override
        public Representation<U> build() {
            return new Representation<>(this);
        }

        public Builder withData(U data) {
            this.data = data;
            return this;
        }

        public Builder addLink(String key, URL value) {
            this.links.put(key, value.toString());
            return this;
        }

        public Builder addMeta(String key, Object value) {
            this.meta.put(key, value);
            return this;
        }
    }

}
