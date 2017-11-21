package com.biffbangpow.census.resources;


import com.biffbangpow.census.Main;
import com.biffbangpow.census.app.App;
import com.biffbangpow.census.config.ConfigAccess;
import com.biffbangpow.census.config.ConfigAccessException;
import com.biffbangpow.census.config.Configuration;
import com.biffbangpow.census.db.CensusDAO;
import com.biffbangpow.census.model.ColumnList;
import com.biffbangpow.census.model.Result;
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
import javax.xml.bind.JAXBException;
import java.io.IOException;

public class ColumnsResourceTest {

    private static final java.util.logging.Logger LOGGER = java.util.logging.Logger.getLogger(ColumnsResourceTest.class
            .getName());

    private Main main;
    private WebTarget columnsTarget;

    @BeforeClass
    public void setUp() throws ConfigAccessException, JAXBException, IOException, InterruptedException {

        Configuration config = (new ConfigAccess()).getConf("src/main/resources/config.yaml");
        CensusDAO dao = new CensusDAO(config);
        App app = new App(config, dao);
        main = new Main(config, app);
        main.start();

        // Client configuration
        Client client = getClient();
        columnsTarget = client.target(main.getBaseURI()).path("columns");
    }

    @AfterClass
    public void tearDown() {
        main.shutdown();
    }

    @Test
    public void test_server_is_started() {
        Assert.assertTrue(main.isStarted());
    }

    @Test
    public void test_get_columns_in_json_format() {
        getColumns(MediaType.APPLICATION_XML_TYPE);
    }

    @Test
    public void test_get_columns_in_xml_format() {
        getColumns(MediaType.APPLICATION_JSON_TYPE);
    }

    private void getColumns(MediaType mediaType) {
        ColumnList columns = columnsTarget.request(mediaType).get(ColumnList.class);
        Assert.assertTrue(columns.size() > 0);
    }


    @Test
    public void test_get_properties_in_xml_format() {

        getProperties(MediaType.APPLICATION_XML_TYPE, "education", 17);
    }

    public void test_get_all_properties() {

        ColumnList columns =
                columnsTarget.request(MediaType.APPLICATION_JSON_TYPE).get(ColumnList.class);
        for (String column: columns.getResponses()) {
            getProperties(MediaType.APPLICATION_JSON_TYPE, column);
        }
    }

    @Test
    public void test_get_properties_in_json() {

        getProperties(MediaType.APPLICATION_JSON_TYPE, "class of worker", 9);
    }

    private void getProperties(MediaType mediaType, String column, int resultSize) {

        Result res = columnsTarget
                .path(column)
                .request(mediaType)
                .get(Result.class);
        Assert.assertEquals(res.getProperties().size(),  resultSize);
    }

    private void getProperties(MediaType mediaType, String column) {

        Result res = columnsTarget
                .path(column)
                .request(mediaType)
                .get(Result.class);
        Assert.assertNotNull(res.getProperties());
    }

    private Client getClient() {
        ClientConfig clientConfig = new ClientConfig();
        clientConfig.register(new LoggingFilter(LOGGER, true));
        return ClientBuilder.newClient(clientConfig);
    }
}
