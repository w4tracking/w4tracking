package org.w4tracking.models;

import java.util.List;
import java.util.Optional;

public interface UserProvider {

    UserModel addUser(String identityId, String username, String identityProvider);

    Optional<UserModel> getUser(String id);

    Optional<UserModel> getUserByIdentityId(String identityId);

    Optional<UserModel> getUserByUsername(String username);

    List<UserModel> getUsers(String filterText);

    List<UserModel> getUsers(String filterText, int offset, int limit);
}
