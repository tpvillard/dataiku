package com.biffbangpow.census.db;


import org.testng.Assert;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionProviderTest {

    @Test
    public void test_connection_is_available() throws SQLException {

        ConnectionProvider provider = new ConnectionProvider("jdbc:sqlite:C:/sqlite/us-census.db");
        Connection cnx = provider.get();
        Assert.assertFalse(cnx.isClosed());
        cnx.close();
    }
}
