package org.w4tracking.representations.idm;

import org.w4tracking.common.Builder;

public class GenericLinksRepresentation {

    private final String self;
    private final String meta;
    private final String related;

    private GenericLinksRepresentation(GenericLinksBuilder builder) {
        this.self = builder.self;
        this.meta = builder.meta;
        this.related = builder.related;
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

    public static class GenericLinksBuilder implements Builder<GenericLinksRepresentation> {
        private String self;
        private String meta;
        private String related;

        public GenericLinksBuilder self(String self) {
            this.self = self;
            return this;
        }

        public GenericLinksBuilder meta(String meta) {
            this.meta = meta;
            return this;
        }

        public GenericLinksBuilder related(String related) {
            this.related = related;
            return this;
        }

        @Override
        public GenericLinksRepresentation build() {
            return new GenericLinksRepresentation(this);
        }
    }
}
