package org.w4tracking;

import org.w4tracking.models.CompanyModel;
import org.w4tracking.models.CompanyProvider;
import org.w4tracking.models.utils.ModelToRepresentation;
import org.w4tracking.representations.idm.*;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;

@Stateless
public class DefaultCompanyResource implements CompanyResource {

    @Inject
    private CompanyProvider companyProvider;

    @Context
    private UriInfo uriInfo;

    @Override
    public Response createCompany(ItemRepresentation<CompanyAttributesRepresentation> rep) {
        CompanyAttributesRepresentation attributes = rep.getData().getAttributes();
        CompanyModel company = companyProvider.addCompany(attributes.getName());

        URI self = uriInfo.getBaseUriBuilder()
                .path(CompanyResource.class, "getCompany")
                .build(company.getId());
        LinksRepresentation links = new LinksRepresentation.Builder().withSelf(self).build();
        CompanyAttributesRepresentation companyAttributes = ModelToRepresentation.toRepresentation(company, true);
        DataRepresentation<AttributesRepresentation> data = ResourceUtils.buildData(company, companyAttributes, links);
        return Response.created(self).entity(data).build();
    }

    @Override
    public void updateCompany(ItemRepresentation<CompanyAttributesRepresentation> rep) {

    }

    @Override
    public CollectionRepresentation<CompanyAttributesRepresentation> getCompanies() {
        return null;
    }

    @Override
    public ItemRepresentation<CompanyAttributesRepresentation> getCompany(String companyId) {
        return null;
    }
}
