package org.w4tracking.models.jpa;

import org.w4tracking.models.UserModel;
import org.w4tracking.models.UserProvider;
import org.w4tracking.models.jpa.entities.UserEntity;
import org.w4tracking.models.utils.ModelUtils;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.UUID;

@Stateless
public class JpaUserProvider implements UserProvider {

    @PersistenceContext
    private EntityManager em;

    @Override
    public UserModel addUser(String username, String identityId, String identityProvider) {
        UserEntity entity = new UserEntity();
        entity.setId(ModelUtils.generateId());
        entity.setUsername(username);
        entity.setIdentityId(identityId);
        entity.setIdentityProvider(identityProvider);
        em.persist(entity);
        return new UserAdapter(em, entity);
    }

    @Override
    public UserModel getUser(String id) {
        return null;
    }

    @Override
    public UserModel getUserByIdentityId(String identityId) {
        return null;
    }
}
