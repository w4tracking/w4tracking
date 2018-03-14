package org.w4tracking;

import io.swagger.annotations.Api;
import org.w4tracking.models.transaction.W4TrackingTransaction;
import org.w4tracking.models.transaction.W4Transactional;
import org.w4tracking.representations.idm.*;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.util.*;

@RequestScoped
//@W4Transactional
public class DefaultCompanyResource implements CompanyResource {

//    @Inject
//    private W4TrackingTransaction transaction;

    @Override
    public Response createCompany(ItemRepresentation<CompanyAttributes> rep) {
        DataRepresentation<CompanyAttributes> data = rep.getData();

        ItemRepresentation<CompanyAttributes> company = new ItemRepresentation.Builder<CompanyAttributes>()
                .withData(
                        new DataRepresentation.Builder<CompanyAttributes>()
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
                                        new CompanyAttributes.Builder()
                                                .withName("Wolsnut4")
                                                .build()
                                )
                                .build()
                ).build();

        return Response.ok(company).build();
    }

    @Override
    public void updateCompany(ItemRepresentation<CompanyAttributes> rep) {

    }

    @Override
    public CollectionRepresentation<CompanyAttributes> getCompanies() {
        DataRepresentation<CompanyAttributes> data = new DataRepresentation.Builder<CompanyAttributes>()
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
                        new CompanyAttributes.Builder()
                                .withName("Wolsnut4")
                                .build()
                )
                .build();

        return new CollectionRepresentation.Builder<CompanyAttributes>()
                .withMeta(new HashMap<>())
                .withLinks(new HashMap<>())
                .withData(Collections.singletonList(data))
                .build();
    }

    @Override
    public ItemRepresentation<CompanyAttributes> getCompany(String companyId) {
        return null;
    }
}
