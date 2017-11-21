package com.biffbangpow.census.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * A property for a given column.
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Property {

    private String name;

    private int count;

    private double age;

    public String getName() {
        return name;
    }

    public int getCount() {
        return count;
    }

    public double getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setAge(double age) {
        this.age = age;
    }

    public static Property of(String name, int count, double age) {
        Property property = new Property();
        property.setAge(age);
        property.setCount(count);
        property.setName(name);
        return property;
    }
}
