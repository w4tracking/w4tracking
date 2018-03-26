package org.w4tracking.representations.idm;

public class CompanyAttributesRepresentation implements AttributesRepresentation {

    private String name;

    public CompanyAttributesRepresentation() {

    }

    public static class SpaceRelationships {
        private SpaceOwnedBy ownedBy;

        public SpaceOwnedBy getOwnedBy() {
            return ownedBy;
        }

        public void setOwnedBy(SpaceOwnedBy ownedBy) {
            this.ownedBy = ownedBy;
        }
    }

    public static class SpaceOwnedBy {
        private UserRepresentation.UserData data;
        private GenericLinksRepresentation links;

        public UserRepresentation.UserData getData() {
            return data;
        }

        public void setData(UserRepresentation.UserData data) {
            this.data = data;
        }

        public GenericLinksRepresentation getLinks() {
            return links;
        }

        public void setLinks(GenericLinksRepresentation links) {
            this.links = links;
        }
    }

    private CompanyAttributesRepresentation(Builder builder) {
        this.name = builder.name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static class Builder {
        private String name;

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public CompanyAttributesRepresentation build() {
            return new CompanyAttributesRepresentation(this);
        }
    }
}
