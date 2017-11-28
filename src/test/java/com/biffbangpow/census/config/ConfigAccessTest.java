package com.biffbangpow.census.config;

import org.testng.Assert;
import org.testng.annotations.Test;

public class ConfigAccessTest {

    private ConfigAccess configAccess = new ConfigAccess();

    @Test
    public void test_config_is_read_from_file() throws ConfigAccessException {
        Configuration config = configAccess.getConf("src/main/resources/config.yaml");
        Assert.assertEquals(config.getDbUrl(), "jdbc:sqlite:src/main/resources/us-census.db");
        Assert.assertEquals(config.getDbName(), "census_learn_sql");
        Assert.assertEquals(config.getColumns().size(), 41);
    }


}
