package com.biffbangpow.census.db;

/**
 * An exception raised when db in error.
 *
 */
public class CensusDAOException extends RuntimeException {

	/**
	 * Creates a CensusDAO exception.
	 * @param msg the associated message
	 * @param t the associated throwable
	 */
	public CensusDAOException(String msg, Throwable t) {
		super(msg, t);
	}

}
