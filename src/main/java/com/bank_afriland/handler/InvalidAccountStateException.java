package com.bank_afriland.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * <blockquote><pre>
 * Author   : @Dountio
 * LinkedIn : @SidofDountio
 * GitHub   : @SidofDountio
 * Version  : V1.0
 * Email    : sidofdountio406@gmail.com
 * Licence  : All Right Reserved BIS
 * Since    : 2/19/25
 * </blockquote></pre>
 */


@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidAccountStateException extends RuntimeException {
    public InvalidAccountStateException(String accountIsNotActive) {
        super(accountIsNotActive);
    }
}
