package com.bank_afriland.handler;

public class APIException extends RuntimeException {
    public APIException(String message) {
        super(message);
    }
}
