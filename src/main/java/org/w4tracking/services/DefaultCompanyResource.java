package org.w4tracking.services;

import org.w4tracking.CompanyResource;
import org.w4tracking.ResourceUtils;
import org.w4tracking.models.CompanyModel;
import org.w4tracking.models.CompanyProvider;
import org.w4tracking.models.utils.ModelToRepresentation;
import org.w4tracking.representations.idm.*;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import java.net.URI;

@Transactional
@ApplicationScoped
public class DefaultCompanyResource implements CompanyResource {

    @Context
    private UriInfo uriInfo;

    @Inject
    private CompanyProvider companyProvider;

    @Override
    public ItemRepresentation<CompanyAttributesRepresentation> createCompany(ItemRepresentation<CompanyAttributesRepresentation> rep) {
        CompanyAttributesRepresentation attributes = rep.getData().getAttributes();
        CompanyModel company = companyProvider.addCompany(attributes.getName());

        URI self = uriInfo.getBaseUriBuilder()
                .path(DefaultCompanyResource.class, "getCompany")
                .build(company.getId());
        LinksRepresentation links = new LinksRepresentation.Builder().withSelf(self).build();
        CompanyAttributesRepresentation companyAttributes = ModelToRepresentation.toRepresentation(company, true);
        DataRepresentation<CompanyAttributesRepresentation> data = ResourceUtils.buildData(company, companyAttributes, links);
        return new ItemRepresentation.Builder<CompanyAttributesRepresentation>().withData(data).build();
    }

    @Override
    public void updateCompany(ItemRepresentation<CompanyAttributesRepresentation> rep) {
        throw new UnsupportedOperationException();
    }

    @Override
    public CollectionRepresentation<CompanyAttributesRepresentation> getCompanies() {
        throw new UnsupportedOperationException();
    }

    @Override
    public ItemRepresentation<CompanyAttributesRepresentation> getCompany(String companyId) {
        throw new UnsupportedOperationException();
    }

}
