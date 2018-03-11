package org.w4tracking.services.resources;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.w4tracking.services.resources.representations.idm.CompanyRepresentation;
import org.w4tracking.services.resources.representations.idm.GenericDataRepresentation;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/companies")
@Consumes(MediaType.APPLICATION_JSON)
@Api(value = "Company Resource", description = "Company REST API", consumes = "application/json")
public interface CompanyResource {

    @POST
    @ApiOperation(value = "Create Company")
    Response createCompany(CompanyRepresentation rep);

    @PUT
    @ApiOperation(value = "Update Company")
    void updateCompany(CompanyRepresentation rep);

    @GET
    @ApiOperation(value = "Company list")
    GenericDataRepresentation<List<CompanyRepresentation>> getCompanies();

    @GET
    @Path("/{companyId}")
    @ApiOperation(value = "Get Company")
    CompanyRepresentation getCompany(@PathParam("companyId") String companyId);

}
