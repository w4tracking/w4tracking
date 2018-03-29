package org.w4tracking.services;

import org.w4tracking.CompaniesResource;
import org.w4tracking.models.CompanyModel;
import org.w4tracking.models.CompanyProvider;
import org.w4tracking.models.ModelType;
import org.w4tracking.models.utils.ModelToRepresentation;
import org.w4tracking.representations.idm.CompaniesRepresentation;
import org.w4tracking.representations.idm.CompanyRepresentation;
import org.w4tracking.representations.idm.CompanySearchQueryRepresentation;
import org.w4tracking.representations.idm.LinksRepresentation;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.ForbiddenException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@ApplicationScoped
public class DefaultCompanyResource implements CompaniesResource {

    @Context
    private UriInfo uriInfo;

    @Inject
    private CompanyProvider companyProvider;

    private CompanyRepresentation.CompanyData toData(CompanyModel model, CompanyRepresentation.CompanyAttributesRepresentation attributes) {
        URI self = uriInfo
                .getAbsolutePathBuilder()
                .path(model.getId())
                .build();
        LinksRepresentation links = new LinksRepresentation();
        links.setSelf(self.toString());

        CompanyRepresentation.CompanyData data = new CompanyRepresentation.CompanyData();
        data.setId(model.getId());
        data.setType(ModelType.COMPANY.getAlias());
        data.setLinks(links);
        data.setAttributes(attributes);
        return data;
    }

    @Override
    public CompanyRepresentation createCompany(CompanyRepresentation rep) {
        CompanyRepresentation.CompanyAttributesRepresentation attributes = rep.getData().getAttributes();
        CompanyModel company = companyProvider.addCompany(attributes.getName());

        CompanyRepresentation.CompanyData data = toData(company, ModelToRepresentation.toRepresentation(company, true));
        return new CompanyRepresentation(data);
    }

    @Override
    public CompaniesRepresentation getCompanies(
            String companyId,
            String filterText,
            Integer offset,
            Integer limit
    ) {
        if (companyId != null) {
            return companyProvider.getCompany(companyId)
                    .map(model -> toData(model, ModelToRepresentation.toRepresentation(model, false)))
                    .map(Collections::singletonList)
                    .map(CompaniesRepresentation::new)
                    .orElseGet(() -> new CompaniesRepresentation(Collections.emptyList()));
        } else if (filterText != null) {
            List<CompanyRepresentation.CompanyData> data = companyProvider.getCompanies(filterText, offset, limit)
                    .stream()
                    .map(model -> toData(model, ModelToRepresentation.toRepresentation(model, false)))
                    .collect(Collectors.toList());
            return new CompaniesRepresentation(data);
        } else {
            throw new BadRequestException("You need to pass valid parameters");
        }
    }

    @Override
    public CompaniesRepresentation searchCompanies(CompanySearchQueryRepresentation query) {
        throw new ForbiddenException();
    }

    @Override
    public CompanyRepresentation getCompany(String companyId) {
        CompanyModel companyModel = companyProvider.getCompany(companyId).orElseThrow(NotFoundException::new);
        CompanyRepresentation.CompanyData data = toData(companyModel, ModelToRepresentation.toRepresentation(companyModel, true));
        return new CompanyRepresentation(data);
    }

    @Override
    public void updateCompany(String companyId, CompaniesRepresentation rep) {
        companyProvider.getCompany(companyId).orElseThrow(NotFoundException::new);
    }

    @Override
    public void deleteCompany(String companyId, CompaniesRepresentation rep) {
        throw new ForbiddenException();
    }

}
