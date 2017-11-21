package com.biffbangpow.census.db;


import com.biffbangpow.census.config.Configuration;
import com.biffbangpow.census.model.Property;
import com.biffbangpow.census.model.Result;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CensusDAO {

    private static final MessageFormat QUERY_WITH_LIMIT = new MessageFormat("select \"{0}\" as property, count(*) as count, avg(age) as age from {1} group by \"{0}\" order by count desc limit 100");
    private static final MessageFormat QUERY_NO_LIMIT = new MessageFormat("select \"{0}\" as property, count(*) as count, avg(age) as age from {1} group by \"{0}\" order by count desc");
    private final Configuration config;
    private final ConnectionProvider provider;


    public CensusDAO(Configuration config) {

        this.config = config;
        provider = new ConnectionProvider(config.getDbUrl());
    }

    List<Property> getPropertiesForColumn(String column) {

        return getPropertiesForQuery(buildQueryWithLimit(column));
    }

    public Result getResultForColumn(String column) {

        Result res = new Result();
        List<Property> properties = getPropertiesForQuery(buildQueryNoLimit(column));
        int rowCount = properties.stream().mapToInt(Property::getCount).sum();
        if (properties.size() > config.getDisplayedProperties()) {
            int skippedValuesCount = properties.size() - config.getDisplayedProperties();
            List<Property> keptValues = properties.stream().limit(config.getDisplayedProperties()).collect(Collectors.toList());
            int remainingRows = keptValues.stream().mapToInt(Property::getCount).sum();
            int skippedRowsCount = rowCount - remainingRows;
            res.setSkippedRowsCount(skippedRowsCount);
            res.setSkippedValuesCount(skippedValuesCount);
            res.setProperties(keptValues);
        } else {
            res.setProperties(properties);
        }
        return res;
    }

    private List<Property> getPropertiesForQuery(String query) {

        List<Property> properties = new ArrayList<>();

        // FIXME connection pooling would be nice.
        Connection con = provider.get();
        try (Statement stmt = con.createStatement()) {

            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String name = rs.getString("property");
                int count = rs.getInt("count");
                double age = rs.getDouble("age");
                // Data is not clean, get rid of null rows.
                if (name != null) {
                    properties.add(Property.of(name, count, round(age)));
                }
            }
        } catch (SQLException ex) {
            throw new CensusDAOException(ex.getMessage(), ex.getCause());
        }
        return properties;
    }

    String buildQueryWithLimit(String column) {
        Object[] args = {column, config.getDbName()};
        return QUERY_WITH_LIMIT.format(args);
    }

    String buildQueryNoLimit(String column) {
        Object[] args = {column, config.getDbName()};
        return QUERY_NO_LIMIT.format(args);
    }

    private static double round(double value) {
        return (double) Math.round(value * 100d) / 100d;
    }
}
