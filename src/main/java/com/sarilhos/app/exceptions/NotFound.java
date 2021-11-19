package com.sarilhos.app.exceptions;

import org.jboss.resteasy.reactive.RestResponse;

public class NotFound extends TodoException {

    public NotFound() {
        super("not found");
    }

    public RestResponse.Status getCode() {
        return RestResponse.Status.NOT_FOUND;
    }

}
