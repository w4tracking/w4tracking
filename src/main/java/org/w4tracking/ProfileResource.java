package org.w4tracking;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.w4tracking.representations.idm.CollectionRepresentation;
import org.w4tracking.representations.idm.CompanyAttributesRepresentation;
import org.w4tracking.representations.idm.ItemRepresentation;
import org.w4tracking.representations.idm.UserAttributesRepresentation;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/profile")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "Profile Resource", description = "Profile REST API", consumes = "application/json")
public interface ProfileResource {

    @GET
    @ApiOperation(value = "Get Profile")
    ItemRepresentation<UserAttributesRepresentation> getProfile();

}
