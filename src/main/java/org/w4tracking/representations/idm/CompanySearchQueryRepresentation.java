package org.w4tracking.representations.idm;

public class CompanySearchQueryRepresentation {

    public static enum UserRole {
        OWNER,
        COLLABORATOR
    }

    private CompanySearchQueryData data;

    public CompanySearchQueryData getData() {
        return data;
    }

    public void setData(CompanySearchQueryData data) {
        this.data = data;
    }

    public static class CompanySearchQueryData {
        private String userId;
        private UserRole userRole;

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public UserRole getUserRole() {
            return userRole;
        }

        public void setUserRole(UserRole userRole) {
            this.userRole = userRole;
        }
    }

}
