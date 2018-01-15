package com.biffbangpow.census.db;


import com.biffbangpow.census.config.ConfigAccess;
import com.biffbangpow.census.config.ConfigAccessException;
import com.biffbangpow.census.config.Configuration;
import com.biffbangpow.census.model.Property;
import com.biffbangpow.census.model.Result;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.SQLException;
import java.util.List;

public class CensusDAOTest {

    private CensusDAO dao;

    @BeforeClass
    public void setup() throws ConfigAccessException {
        ConfigAccess access = new ConfigAccess();
        Configuration config = access.getConf("src/main/resources/config.yaml");
        dao = new CensusDAO(config);
    }


    @Test
    public void test_query_with_limit_is_formatted() {
        Assert.assertEquals(dao.buildQueryWithLimit("education"), "select \"education\" as property, count(*) as count, avg(age) as age from census_learn_sql group by \"education\" order by count desc limit 100");
    }

    @Test
    public void test_query_without_limit_is_formatted() {
        Assert.assertEquals(dao.buildQueryNoLimit("education"), "select \"education\" as property, count(*) as count, avg(age) as age from census_learn_sql group by \"education\" order by count desc");
    }

    @Test
    public void test_get_educations() throws SQLException {

        List<Property> properties = dao.getPropertiesForColumn("education");
        Assert.assertEquals(properties.size(), 17);
    }

    @Test
    public void test_get_class_of_workers() throws SQLException {

        List<Property> properties = dao.getPropertiesForColumn("class of worker");
        Assert.assertEquals(properties.size(), 9);
    }

    @Test
    public void get_result_for_weight() throws SQLException {

        Result res = dao.getResultForColumn("weight");
        Assert.assertEquals(res.getProperties().size(), 100);
        Assert.assertEquals(res.getSkippedValuesCount(), 99700);
        Assert.assertEquals(res.getSkippedRowsCount(), 197197);
    }
}
