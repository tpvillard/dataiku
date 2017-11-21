package com.biffbangpow.census.resources;

import com.biffbangpow.census.db.CensusDAOException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Exception mapper.
 */
@Provider
public class CensusDAOExceptionMapper implements ExceptionMapper<CensusDAOException>
{
   public Response toResponse(CensusDAOException exception)
   {
      return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
              .entity(exception.getMessage())
              .type("text/plain").build();
   }
}
