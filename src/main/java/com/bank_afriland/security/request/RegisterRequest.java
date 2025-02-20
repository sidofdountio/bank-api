package com.bank_afriland.security.request;

/**
 * <blockquote><pre>
 * Author   : @Dountio
 * LinkedIn : @SidofDountio
 * GitHub   : @SidofDountio
 * Version  : V1.0
 * Email    : sidofdountio406@gmail.com
 * Since    : 2/17/25
 * </blockquote></pre>
 */

public record RegisterRequest(
        String lastName,
        String email,
        String password
//        String address,
//        String phone
) {

}
