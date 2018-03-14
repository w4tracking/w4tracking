package org.w4tracking;

import org.w4tracking.representations.idm.*;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.core.Response;
import java.util.*;

@RequestScoped
//@W4Transactional
public class DefaultCompanyResource implements CompanyResource {

//    @Inject
//    private W4TrackingTransaction transaction;

    @Override
    public Response createCompany(ItemRepresentation<CompanyAttributesRepresentation> rep) {
        DataRepresentation<CompanyAttributesRepresentation> data = rep.getData();

        ItemRepresentation<CompanyAttributesRepresentation> company = new ItemRepresentation.Builder<CompanyAttributesRepresentation>()
                .withData(
                        new DataRepresentation.Builder<CompanyAttributesRepresentation>()
                                .withId(UUID.randomUUID().toString())
                                .withType("company")
                                .withLinks(
                                        new LinksRepresentation.Builder()
                                                .withMeta("withMeta")
                                                .withRelated("withRelated")
                                                .withSelf("withSelf")
                                                .build()
                                )
                                .withAttributes(
                                        new CompanyAttributesRepresentation.Builder()
                                                .withName("Wolsnut4")
                                                .build()
                                )
                                .build()
                ).build();

        return Response.ok(company).build();
    }

    @Override
    public void updateCompany(ItemRepresentation<CompanyAttributesRepresentation> rep) {

    }

    @Override
    public CollectionRepresentation<CompanyAttributesRepresentation> getCompanies() {
        DataRepresentation<CompanyAttributesRepresentation> data = new DataRepresentation.Builder<CompanyAttributesRepresentation>()
                .withId(UUID.randomUUID().toString())
                .withType("company")
                .withLinks(
                        new LinksRepresentation.Builder()
                                .withMeta("withMeta")
                                .withRelated("withRelated")
                                .withSelf("withSelf")
                                .build()
                )
                .withAttributes(
                        new CompanyAttributesRepresentation.Builder()
                                .withName("Wolsnut4")
                                .build()
                )
                .build();

        return new CollectionRepresentation.Builder<CompanyAttributesRepresentation>()
                .withMeta(new HashMap<>())
                .withLinks(new HashMap<>())
                .withData(Collections.singletonList(data))
                .build();
    }

    @Override
    public ItemRepresentation<CompanyAttributesRepresentation> getCompany(String companyId) {
        return null;
    }
}
