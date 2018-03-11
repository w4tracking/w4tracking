package org.w4tracking.services.resources;

import org.w4tracking.services.resources.models.transaction.W4TrackingTransaction;
import org.w4tracking.services.resources.models.transaction.W4Transactional;
import org.w4tracking.services.resources.representations.idm.CompanyRepresentation;
import org.w4tracking.services.resources.representations.idm.GenericDataRepresentation;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.util.List;

@RequestScoped
@W4Transactional
public class DefaultCompanyResource implements CompanyResource {

    @Inject
    private W4TrackingTransaction transaction;

    @Override
    public Response createCompany(CompanyRepresentation rep) {
        return null;
    }

    @Override
    public void updateCompany(CompanyRepresentation rep) {

    }

    @Override
    public GenericDataRepresentation<List<CompanyRepresentation>> getCompanies() {
        return null;
    }

    @Override
    public CompanyRepresentation getCompany(String companyId) {
        return null;
    }

}
