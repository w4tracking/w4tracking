package org.w4tracking;

import org.eclipse.microprofile.rest.client.RestClientBuilder;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.w4tracking.representations.idm.CompanyAttributes;
import org.w4tracking.representations.idm.DataRepresentation;
import org.w4tracking.representations.idm.ItemRepresentation;
import org.w4tracking.representations.idm.LinksRepresentation;
import org.wildfly.swarm.arquillian.DefaultDeployment;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(Arquillian.class)
@DefaultDeployment
public class CompanyResourceTest {

    @Test
    @RunAsClient
    public void createCompanyTest() throws MalformedURLException {
        String apiUrl = "http://localhost:8080/api/companies";

        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(apiUrl);

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

        Response response = target.request(MediaType.APPLICATION_JSON).post(Entity.json(company));

        assertThat(response.getStatus()).isEqualTo(Response.Status.OK.getStatusCode());
    }

}