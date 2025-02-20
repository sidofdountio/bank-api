package com.bank_afriland.security.dto;

import com.bank_afriland.security.model.Users;
import com.bank_afriland.security.request.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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

@Service
@RequiredArgsConstructor
public class UsersMapper {
    @Value("${application.image}")
    String DEFAULT_USER_IMAGE_PROFILE;
    private final PasswordEncoder passwordEncoder;


        public Users toUsers(RegisterRequest request) {
        return Users.builder()
                .lastName(request.lastName())
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .enable(false)
                .accountLocked(false)
                .imageUrl(DEFAULT_USER_IMAGE_PROFILE)
                .build();
    }

//    public Users toUsers(RegisterRequest request) {
//        return new  Users(request.lastName(), request.email(), passwordEncoder.encode(request.password()), request.phone(), request.address(), DEFAULT_USER_IMAGE_PROFILE);
//    }
}
