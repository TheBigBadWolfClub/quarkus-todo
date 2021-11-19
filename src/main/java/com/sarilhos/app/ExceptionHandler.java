package com.sarilhos.app;


import org.jboss.logging.Logger;
import org.jboss.resteasy.reactive.RestResponse;
import org.jboss.resteasy.reactive.server.ServerExceptionMapper;

class ExceptionHandler {
    private static final Logger logger = Logger.getLogger(ExceptionHandler.class);

    @ServerExceptionMapper
    public RestResponse<String> mapException(Exception e) {
        logger.error(e);
        return RestResponse.status(RestResponse.Status.INTERNAL_SERVER_ERROR, "server error, try later");
    }
}