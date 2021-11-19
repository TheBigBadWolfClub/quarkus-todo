package com.sarilhos.app.exceptions;

import org.jboss.resteasy.reactive.RestResponse;

public class InvalidRequest extends TodoException {

    public InvalidRequest() {
        super("invalid request");
    }

    public InvalidRequest(String message) {
        super(message);
    }

    public RestResponse.Status getCode() {
        return RestResponse.Status.CONFLICT;
    }
}
