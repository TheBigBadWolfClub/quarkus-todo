package com.sarilhos.app.exceptions;

import org.jboss.resteasy.reactive.RestResponse;

public class NotImplemented extends TodoException {

    public NotImplemented() {
        super("operation not supported");
    }

    public RestResponse.Status getCode() {
        return RestResponse.Status.NOT_IMPLEMENTED;
    }

}
