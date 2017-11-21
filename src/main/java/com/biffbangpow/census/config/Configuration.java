package com.biffbangpow.census.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Configuration for the app.
 * <p/>
 * This object must conform to javabean standard so that it can be loaded by the yaml parser.
 */
public class Configuration {

    private String dbUrl;
    private String dbName;
    private List<String> columns = new ArrayList<>();
    private int port;
    private int displayedProperties;
    private String docRoot;

    public String getDbUrl() {
        return dbUrl;
    }

    public void setDbUrl(String dbUrl) {
        this.dbUrl = dbUrl;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public List<String> getColumns() {
        return columns;
    }

    public void setColumns(List<String> columns) {
        this.columns = columns;
    }

    public int getDisplayedProperties() {
        return displayedProperties;
    }

    public void setDisplayedProperties(int displayedProperties) {
        this.displayedProperties = displayedProperties;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getDocRoot() {
        return docRoot;
    }

    public void setDocRoot(String docRoot) {
        this.docRoot = docRoot;
    }
}
