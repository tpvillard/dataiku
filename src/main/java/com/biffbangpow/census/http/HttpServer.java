package com.biffbangpow.census.http;

import java.net.URI;

/**
 * An http server.
 */
public interface HttpServer {

    /**
     * shuts down the server.
     */
    void shutdown();

    /**
     * Returns the api base uri.
     *
     * @return the api base uri.
     */
    URI getBaseURI();
}
