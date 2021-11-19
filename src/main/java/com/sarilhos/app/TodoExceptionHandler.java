package com.sarilhos.app;


import com.sarilhos.app.exceptions.TodoException;
import org.jboss.logging.Logger;
import org.jboss.resteasy.reactive.RestResponse;
import org.jboss.resteasy.reactive.server.ServerExceptionMapper;

class TodoExceptionHandler {
    private static final Logger logger = Logger.getLogger(TodoExceptionHandler.class);

    @ServerExceptionMapper
    public RestResponse<String> mapException(TodoException tdException) {
        logger.error(tdException);
        return RestResponse.status(tdException.getCode(), tdException.getMessage());
    }
}