package org.w4tracking.representations.idm;

public class LinksRepresentation {

    private String self;
    private String meta;
    private String related;

    public LinksRepresentation() {

    }

    private LinksRepresentation(Builder builder) {
        this.setSelf(builder.self);
        this.setMeta(builder.meta);
        this.setRelated(builder.related);
    }

    public String getSelf() {
        return self;
    }

    public String getMeta() {
        return meta;
    }

    public String getRelated() {
        return related;
    }

    public void setSelf(String self) {
        this.self = self;
    }

    public void setMeta(String meta) {
        this.meta = meta;
    }

    public void setRelated(String related) {
        this.related = related;
    }

    public static class Builder {
        private String self;
        private String meta;
        private String related;

        public Builder withSelf(String self) {
            this.self = self;
            return this;
        }

        public Builder withMeta(String meta) {
            this.meta = meta;
            return this;
        }

        public Builder withRelated(String related) {
            this.related = related;
            return this;
        }

        public LinksRepresentation build() {
            return new LinksRepresentation(this);
        }
    }
}
