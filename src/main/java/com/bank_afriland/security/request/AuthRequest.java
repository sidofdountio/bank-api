package com.bank_afriland.security.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
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
 * Since    : 2/17/25
 * </blockquote></pre>
 */

@Getter
@Setter
@Builder
public class AuthRequest {
 @NotEmpty(message = "Email cannot be empty")
 @NotBlank(message = "Email cannot be null")
 @Email(message = "Not a valid email.")
 private String email;
 @NotEmpty(message = "Password cannot be empty")
 @Size(min = 8, message = "Password should be 8 character long minimum")
 private String password;


}
