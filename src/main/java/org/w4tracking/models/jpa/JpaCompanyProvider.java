package org.w4tracking.models.jpa;

import org.w4tracking.models.CompanyModel;
import org.w4tracking.models.CompanyProvider;
import org.w4tracking.models.jpa.entities.CompanyEntity;
import org.w4tracking.models.transaction.W4Transactional;
import org.w4tracking.models.utils.ModelUtils;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@RequestScoped
@W4Transactional
public class JpaCompanyProvider implements CompanyProvider {

    @Inject
    private EntityManager em;

    @Override
    public CompanyModel addCompany(String name) {
        CompanyEntity entity = new CompanyEntity();
        entity.setId(ModelUtils.generateId());
        entity.setName(name);
        em.persist(entity);
        return new CompanyAdapter(em, entity);
    }

}
