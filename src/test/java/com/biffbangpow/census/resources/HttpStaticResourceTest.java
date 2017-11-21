package com.biffbangpow.census.resources;


import com.biffbangpow.census.Main;
import com.biffbangpow.census.app.App;
import com.biffbangpow.census.config.ConfigAccess;
import com.biffbangpow.census.config.Configuration;
import com.biffbangpow.census.db.CensusDAO;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.filter.LoggingFilter;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;

public class HttpStaticResourceTest {

    private static final java.util.logging.Logger LOGGER = java.util.logging.Logger.getLogger(HttpStaticResourceTest.class
            .getName());

    private Main main;
    private WebTarget rootTarget;

    @BeforeClass
    public void setUp() throws InterruptedException {

        Configuration config = (new ConfigAccess()).getConf("src/main/resources/config.yaml");
        config.setDocRoot("webapp");
        CensusDAO dao = new CensusDAO(config);
        App app = new App(config, dao);
        main = new Main(config, app);
        main.start();

        // Client configuration
        Client client = getClient();
        URI uri = UriBuilder.fromUri("http://localhost/hello.html").port(config.getPort()).build();
        rootTarget = client.target(uri);
    }

    @AfterClass
    public void tearDown() {
        main.shutdown();
    }

    @Test
    public void test_get_static_resource() {
        Response response =  rootTarget.request(MediaType.APPLICATION_OCTET_STREAM_TYPE).get();
        Assert.assertEquals(response.getStatus(), 200);
        Assert.assertEquals(response.readEntity(String.class), "<head>Hello World</head>");
    }

    private Client getClient() {

        ClientConfig clientConfig = new ClientConfig();
        clientConfig.register(new LoggingFilter(LOGGER, true));
        return ClientBuilder.newClient(clientConfig);
    }
}
