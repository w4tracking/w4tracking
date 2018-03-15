package org.w4tracking;

import org.w4tracking.models.CompanyModel;
import org.w4tracking.models.CompanyProvider;
import org.w4tracking.models.transaction.W4Transactional;
import org.w4tracking.models.utils.ModelToRepresentation;
import org.w4tracking.representations.idm.*;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;

@RequestScoped
@W4Transactional
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
//        DataRepresentation<CompanyAttributesRepresentation> data = new DataRepresentation.Builder<CompanyAttributesRepresentation>()
//                .withId(UUID.randomUUID().toString())
//                .withType("company")
//                .withLinks(
//                        new LinksRepresentation.Builder()
//                                .withMeta("withMeta")
//                                .withRelated("withRelated")
//                                .withSelf("withSelf")
//                                .build()
//                )
//                .withAttributes(
//                        new CompanyAttributesRepresentation.Builder()
//                                .withName("Wolsnut4")
//                                .build()
//                )
//                .build();
//
//        return new CollectionRepresentation.Builder<CompanyAttributesRepresentation>()
//                .withMeta(new HashMap<>())
//                .withLinks(new HashMap<>())
//                .withData(Collections.singletonList(data))
//                .build();
        return null;
    }

    @Override
    public ItemRepresentation<CompanyAttributesRepresentation> getCompany(String companyId) {
        return null;
    }
}
