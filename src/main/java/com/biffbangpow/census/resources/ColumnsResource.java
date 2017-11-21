package com.biffbangpow.census.resources;

import com.biffbangpow.census.config.Configuration;
import com.biffbangpow.census.db.CensusDAO;
import com.biffbangpow.census.model.ColumnList;
import com.biffbangpow.census.model.Result;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.MediaType.APPLICATION_XML;

/**
 * JAX-RS resource for accessing columns properties.
 */
@Path("columns")
@Produces({APPLICATION_XML, APPLICATION_JSON})
public class ColumnsResource {

    @Inject
    private CensusDAO dao;

    @Inject
    private Configuration config;

    /**
     * Returns the list of properties.
     *
     * @param columnId the column id
     * @return the list of properties
     */
    @GET
    @Path("{id}")
    public Result getResultForColumn(@PathParam("id") String columnId) {

        return dao.getResultForColumn(columnId);
    }

    /**
     * Returns the list of columns.
     *
     * @return the list of columns.
     */
    @GET
    public ColumnList listColumns() {

        return new ColumnList(config.getColumns());
    }
}
