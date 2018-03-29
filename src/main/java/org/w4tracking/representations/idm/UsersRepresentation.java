package org.w4tracking.representations.idm;

import java.util.List;

public class UsersRepresentation {

    private List<UserRepresentation.UserData> data;

    public UsersRepresentation() {

    }

    public UsersRepresentation(List<UserRepresentation.UserData> data) {
        this.data = data;
    }

    public List<UserRepresentation.UserData> getData() {
        return data;
    }

    public void setData(List<UserRepresentation.UserData> data) {
        this.data = data;
    }
}
