package org.w4tracking.models;

public interface UserProvider {

    UserModel addUser(String username, String identityId, String identityProvider);

    UserModel getUser(String id);

    UserModel getUserByIdentityId(String identityId);
}
