package org.w4tracking.representations.idm;

public class CompanyRepresentation {
    private CompanyData data;

    public CompanyRepresentation() {

    }

    public CompanyRepresentation(CompanyData data) {
        this.data = data;
    }

    public CompanyData getData() {
        return data;
    }

    public void setData(CompanyData data) {
        this.data = data;
    }

    public static class CompanyData {
        private String id;
        private String type;
        private LinksRepresentation links;
        private CompanyAttributesRepresentation attributes;
        private CompanyRelationships relationships;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public LinksRepresentation getLinks() {
            return links;
        }

        public void setLinks(LinksRepresentation links) {
            this.links = links;
        }

        public CompanyAttributesRepresentation getAttributes() {
            return attributes;
        }

        public void setAttributes(CompanyAttributesRepresentation attributes) {
            this.attributes = attributes;
        }

        public CompanyRelationships getRelationships() {
            return relationships;
        }

        public void setRelationships(CompanyRelationships relationships) {
            this.relationships = relationships;
        }
    }

    public static class CompanyAttributesRepresentation {
        private String name;
        private String description;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }

    public static class CompanyRelationships {
        private CompanyOwnedBy ownedBy;

        public CompanyOwnedBy getOwnedBy() {
            return ownedBy;
        }

        public void setOwnedBy(CompanyOwnedBy ownedBy) {
            this.ownedBy = ownedBy;
        }
    }

    public static class CompanyOwnedBy {
        private UserRepresentation.UserData data;
        private LinksRepresentation links;

        public UserRepresentation.UserData getData() {
            return data;
        }

        public void setData(UserRepresentation.UserData data) {
            this.data = data;
        }

        public LinksRepresentation getLinks() {
            return links;
        }

        public void setLinks(LinksRepresentation links) {
            this.links = links;
        }
    }

}
