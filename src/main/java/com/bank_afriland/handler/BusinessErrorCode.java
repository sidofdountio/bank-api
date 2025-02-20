package com.bank_afriland.handler;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public enum BusinessErrorCode {
    NO_CODE(0,HttpStatus.NOT_IMPLEMENTED, "No code"),
    INCORRECT_CURRENT_PASSWORD(300,HttpStatus.BAD_REQUEST,"Current password is incorrect"),
    NEW_PASSWORD_DOES_NOT_MATCH(301,HttpStatus.BAD_REQUEST,"The new password does not match"),
    ACCOUNT_LOCKED(302,HttpStatus.FORBIDDEN,"User account is locked"),
    ACCOUNT_DISABLED(303, HttpStatus.FORBIDDEN,"Current account is disable"),

    BAD_CREDENTIALS(304,HttpStatus.FORBIDDEN,"Username or password is incorrect"),
    ACCOUNT_NOT_FOUND(401, HttpStatus.NOT_FOUND,"Account not found"),

    ;
    @Getter
    private final int code;
    @Getter
    private final String description;
    @Getter
    private final HttpStatus httpStatus;

    BusinessErrorCode(int code, HttpStatus httpStatus ,String description ) {
        this.code = code;
        this.description = description;
        this.httpStatus = httpStatus;
    }
}
