package org.w4tracking;

import org.eclipse.microprofile.rest.client.RestClientBuilder;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.jboss.arquillian.junit.Arquillian;
import org.w4tracking.representations.idm.CompanyRepresentation;
import org.wildfly.swarm.arquillian.DefaultDeployment;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.MalformedURLException;
import java.net.URL;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(Arquillian.class)
@DefaultDeployment
public class CompanyResourceTest {

    @Test
    @RunAsClient
    public void test_service_invocation() throws MalformedURLException {
        String apiUrl = "http://localhost:8080/api/companies";
        CompanyResource companyService = RestClientBuilder.newBuilder()
                .baseUrl(new URL(apiUrl))
                .build(CompanyResource.class);

        CompanyRepresentation company = new CompanyRepresentation();

        Response response = companyService.createCompany(company);

        assertThat(response.getStatus()).isEqualTo(Response.Status.CREATED);
        Assert.assertTrue(response.readEntity(String.class).contains("Hello, World!"));
    }

    @Test
    @RunAsClient
    public void test_service_invocation_withParam() {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:8080")
                .path("api").path("greeting")
                .queryParam("name", "Peter");

        Response response = target.request(MediaType.APPLICATION_JSON).get();
        Assert.assertEquals(200, response.getStatus());
        Assert.assertTrue(response.readEntity(String.class).contains("Hello, Peter!"));
    }
}