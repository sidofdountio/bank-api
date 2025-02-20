package com.bank_afriland.project.api;

import com.bank_afriland.reponse.CustomResponse;
import com.bank_afriland.project.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static java.time.LocalDateTime.now;
import static java.util.Map.of;
import static org.springframework.http.HttpStatus.OK;

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

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserAPI {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<CustomResponse> users() throws InterruptedException {
        return new ResponseEntity<>(CustomResponse.builder()
                .timeStamp(now())
                .data(of("users", userService.users()))
                .message("Users retrieved")
                .status(OK)
                .statusCode(OK.value())
                .build(), OK);
    }

    @GetMapping("/user")
    public ResponseEntity<CustomResponse> getUser(@RequestParam(name = "userId") Long userId) {
        return new ResponseEntity<>(CustomResponse.builder()
                .timeStamp(now())
                .data(of("user", userService.user(userId)))
                .message("User retrieved")
                .status(OK)
                .statusCode(OK.value())
                .build(), OK);
    }

    @GetMapping("/me")
// @PreAuthorize("hasAnyAuthority('admin:read','sysadmin:read','user:read','manager:read')")
    public ResponseEntity<CustomResponse> me(Authentication authentication) {
        return new ResponseEntity<>(CustomResponse.builder()
                .timeStamp(now())
                .data(of("user", userService.myProfile(authentication)))
                .message("profile retrieved")
                .status(OK)
                .statusCode(OK.value())
                .build(), OK);
    }
}
