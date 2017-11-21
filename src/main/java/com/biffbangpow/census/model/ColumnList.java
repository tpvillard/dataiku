package com.biffbangpow.census.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * FIXME is there a better alternative to return List<String> with Jax-rs?
 */
@XmlRootElement(name = "columns")
public class ColumnList {

    @XmlElement(name = "column")
    private List<String> responses;

    /**
     * Creates a no args constructor as mandated by JAXB.
     */
    public ColumnList() {
    }

    public ColumnList(List<String> responses) {
        this.responses = responses;
    }

    public List<String> getResponses() {
        return responses;
    }

    public int size() {
        if (responses == null) {
            return 0;
        }
        return responses.size();
    }
}
