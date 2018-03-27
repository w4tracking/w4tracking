package org.w4tracking;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.w4tracking.representations.idm.CompaniesRepresentation;
import org.w4tracking.representations.idm.CompanyAttributesRepresentation;
import org.w4tracking.representations.idm.CompanyRepresentation;
import org.w4tracking.representations.idm.ItemRepresentation;

import javax.enterprise.context.ApplicationScoped;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("companies")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Api(value = "Company Resource", description = "Company REST API", consumes = "application/json")
public interface CompaniesResource {

    @POST
    @Path("/")
    @ApiOperation(value = "Create Company")
    CompanyRepresentation createCompany(@Valid CompanyRepresentation rep);

    @GET
    @Path("/")
    @ApiOperation(value = "Get Companies")
    CompaniesRepresentation getCompanies(
            @QueryParam("userId") String userId,
            @QueryParam("userRole") @DefaultValue("owner") String userRole
    );

    @GET
    @Path("/{companyId}")
    @ApiOperation(value = "Get Company")
    CompaniesRepresentation getCompany(String companyId);

    @PUT
    @Path("/{companyId}")
    @ApiOperation(value = "Update Company")
    void updateCompany(CompaniesRepresentation rep);

    @DELETE
    @Path("/{companyId}")
    @ApiOperation(value = "Update Company")
    void deleteCompany(CompaniesRepresentation rep);

}
