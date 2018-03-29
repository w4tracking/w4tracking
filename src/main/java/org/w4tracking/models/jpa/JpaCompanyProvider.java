package org.w4tracking.models.jpa;

import org.w4tracking.models.CompanyModel;
import org.w4tracking.models.CompanyProvider;
import org.w4tracking.models.jpa.entities.CompanyEntity;
import org.w4tracking.models.utils.ModelUtils;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ApplicationScoped
public class JpaCompanyProvider implements CompanyProvider {

    @PersistenceContext
    private EntityManager em;

    @Override
    public CompanyModel addCompany(String name) {
        CompanyEntity entity = new CompanyEntity();
        entity.setId(ModelUtils.generateId());
        entity.setName(name);
        em.persist(entity);
        return new CompanyAdapter(em, entity);
    }

    @Override
    public Optional<CompanyModel> getCompany(String id) {
        CompanyEntity companyEntity = em.find(CompanyEntity.class, id);
        if (companyEntity == null) return Optional.empty();
        return Optional.of(new CompanyAdapter(em, companyEntity));
    }

    @Override
    public List<CompanyModel> getCompanies(String filterText) {
        return getCompanies(filterText, -1, -1);
    }

    @Override
    public List<CompanyModel> getCompanies(String filterText, int offset, int limit) {
        TypedQuery<CompanyEntity> query = em.createNamedQuery("getCompaniesByFilterText", CompanyEntity.class);
        query.setParameter("filterText", "%" + filterText.toLowerCase());
        if (offset != -1) {
            query.setFirstResult(offset);
        }
        if (limit != -1) {
            query.setMaxResults(limit);
        }
        return query.getResultList()
                .stream()
                .map(entity -> new CompanyAdapter(em, entity))
                .collect(Collectors.toList());
    }

}
