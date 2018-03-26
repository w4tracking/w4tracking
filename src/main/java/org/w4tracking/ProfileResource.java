package org.w4tracking;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.w4tracking.representations.idm.ItemRepresentation;
import org.w4tracking.representations.idm.UserAttributesRepresentation;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/profile")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "Profile Resource", description = "Profile REST API", consumes = "application/json")
public interface ProfileResource {

    @GET
    @Path("/")
    @ApiOperation(value = "Get Profile")
    ItemRepresentation<UserAttributesRepresentation> getProfile();

}
