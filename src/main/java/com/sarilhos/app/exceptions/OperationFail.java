package com.sarilhos.app.exceptions;

import org.jboss.resteasy.reactive.RestResponse;

public class OperationFail extends TodoException {
    public static RestResponse.Status STATUS = RestResponse.Status.INTERNAL_SERVER_ERROR;

    public OperationFail() {
        super("operation fail");
    }

    public OperationFail(String message) {
        super(message);
    }

    public RestResponse.Status getCode() {
        return RestResponse.Status.INTERNAL_SERVER_ERROR;
    }
}
