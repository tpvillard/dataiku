package com.biffbangpow.census.model;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Result {

    private int skippedValuesCount;
    private int skippedRowsCount;
    private List<Property> properties;

    public int getSkippedValuesCount() {
        return skippedValuesCount;
    }

    public void setSkippedValuesCount(int skippedValuesCount) {
        this.skippedValuesCount = skippedValuesCount;
    }

    public int getSkippedRowsCount() {
        return skippedRowsCount;
    }

    public void setSkippedRowsCount(int skippedRowsCount) {
        this.skippedRowsCount = skippedRowsCount;
    }

    public List<Property> getProperties() {
        return properties;
    }

    public void setProperties(List<Property> properties) {
        this.properties = properties;
    }
}
