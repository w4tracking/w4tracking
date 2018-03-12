package org.w4tracking;

import org.w4tracking.models.transaction.W4TrackingTransaction;
import org.w4tracking.models.transaction.W4Transactional;
import org.w4tracking.representations.idm.CompanyRepresentation;
import org.w4tracking.representations.idm.Representation;

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
    public Representation<List<CompanyRepresentation>> getCompanies() {
        return null;
    }

    @Override
    public CompanyRepresentation getCompany(String companyId) {
        return null;
    }

}
