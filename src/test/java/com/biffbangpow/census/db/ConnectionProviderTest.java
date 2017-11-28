package com.biffbangpow.census.db;


import com.biffbangpow.census.config.ConfigAccess;
import com.biffbangpow.census.config.Configuration;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionProviderTest {

    @Test
    public void test_connection_is_available() throws SQLException {

        Configuration config = (new ConfigAccess()).getConf("src/main/resources/config.yaml");

        ConnectionProvider provider = new ConnectionProvider(config.getDbUrl());
        Connection cnx = provider.get();
        Assert.assertFalse(cnx.isClosed());
        cnx.close();
    }
}
