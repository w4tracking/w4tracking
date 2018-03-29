package org.w4tracking.models.jpa;

import org.w4tracking.models.UserModel;
import org.w4tracking.models.UserProvider;
import org.w4tracking.models.jpa.entities.UserEntity;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Transactional
@ApplicationScoped
public class JpaUserProvider implements UserProvider {

    @PersistenceContext
    private EntityManager em;

    @Override
    public UserModel addUser(String identityId, String username, String identityProvider) {
        UserEntity entity = new UserEntity();
        entity.setId(identityId);
        entity.setUsername(username);
        entity.setIdentityId(identityId);
        entity.setIdentityProvider(identityProvider);
        em.persist(entity);
        return new UserAdapter(em, entity);
    }

    @Override
    public Optional<UserModel> getUser(String id) {
        UserEntity userEntity = em.find(UserEntity.class, id);
        if (userEntity == null) return Optional.empty();
        return Optional.of(new UserAdapter(em, userEntity));
    }

    @Override
    public Optional<UserModel> getUserByIdentityId(String identityId) {
        TypedQuery<UserEntity> query = em.createNamedQuery("GetUserByIdentityId", UserEntity.class);
        query.setParameter("identityId", identityId);
        List<UserEntity> resultList = query.getResultList();
        if (resultList.isEmpty()) {
            return Optional.empty();
        } else if (resultList.size() == 1) {
            return Optional.of(new UserAdapter(em, resultList.get(0)));
        } else {
            throw new IllegalStateException("Found more than one user with equal identityId:" + identityId);
        }
    }

    @Override
    public Optional<UserModel> getUserByUsername(String username) {
        TypedQuery<UserEntity> query = em.createNamedQuery("GetUserByUsername", UserEntity.class);
        query.setParameter("username", username);
        List<UserEntity> resultList = query.getResultList();
        if (resultList.isEmpty()) {
            return Optional.empty();
        } else if (resultList.size() == 1) {
            return Optional.of(new UserAdapter(em, resultList.get(0)));
        } else {
            throw new IllegalStateException("Found more than one user with equal username:" + username);
        }
    }

    @Override
    public List<UserModel> getUsers(String filterText) {
        return getUsers(filterText, -1, -1);
    }

    @Override
    public List<UserModel> getUsers(String filterText, int offset, int limit) {
        TypedQuery<UserEntity> query = em.createNamedQuery("getUsersByFilterText", UserEntity.class);
        query.setParameter("filterText", "%" + filterText.toLowerCase());
        if (offset != -1) {
            query.setFirstResult(offset);
        }
        if (limit != -1) {
            query.setMaxResults(limit);
        }
        return query.getResultList()
                .stream()
                .map(entity -> new UserAdapter(em, entity))
                .collect(Collectors.toList());
    }
}
