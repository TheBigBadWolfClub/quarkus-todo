package com.sarilhos.app.exceptions;

import org.jboss.resteasy.reactive.RestResponse;

import java.io.Serializable;

public class TodoException extends Exception implements Serializable {

    private static final long serialVersionUID = 1L;

    private TodoException() {
        super("Server error");
    }

    public TodoException(String msg) {
        super(msg);
    }

    public TodoException(String msg, Exception e) {
        super(msg, e);
    }

    public RestResponse.Status getCode() {
        return RestResponse.Status.INTERNAL_SERVER_ERROR;
    }
}
