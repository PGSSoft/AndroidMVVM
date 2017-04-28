package com.pgssoft.mvvm.services.exceptions;

/**
 * Created by bstokrocki on 31.01.2017.
 */
public class ApiRequestFailureError extends Throwable {
    private int statusCode;

    public ApiRequestFailureError(int statusCode, String message) {
        super(message);
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
