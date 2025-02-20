package com.bank_afriland.security.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * <blockquote><pre>
 * Author   : @Dountio
 * LinkedIn : @SidofDountio
 * GitHub   : @SidofDountio
 * Version  : V1.0
 * Email    : sidofdountio406@gmail.com
 * Licence  : All Right Reserved BIS
 * Since    : 2/17/25
 * </blockquote></pre>
 */

@Getter
@Setter
@Builder
public class AuthResponse {
    private String token;
    private String referenceId;
    private String username;
}
