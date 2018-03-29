package org.w4tracking.representations.idm;

public class UserRepresentation {
    private UserData data;

    public UserRepresentation() {

    }

    public UserRepresentation(UserData data) {
        this.data = data;
    }

    public UserData getData() {
        return data;
    }

    public void setData(UserData data) {
        this.data = data;
    }

    public static class UserData {
        private String id;
        private String type;
        private LinksRepresentation links;
        private UserAttributesRepresentation attributes;

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

        public UserAttributesRepresentation getAttributes() {
            return attributes;
        }

        public void setAttributes(UserAttributesRepresentation attributes) {
            this.attributes = attributes;
        }
    }

    public static class UserAttributesRepresentation {
        private String username;
        private String email;
        private String fullName;
        private String identityId;
        private String identityProvider;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getFullName() {
            return fullName;
        }

        public void setFullName(String fullName) {
            this.fullName = fullName;
        }

        public String getIdentityId() {
            return identityId;
        }

        public void setIdentityId(String identityId) {
            this.identityId = identityId;
        }

        public String getIdentityProvider() {
            return identityProvider;
        }

        public void setIdentityProvider(String identityProvider) {
            this.identityProvider = identityProvider;
        }
    }
}
