package com.bank_afriland.security;

import com.bank_afriland.reponse.CustomResponse;
import com.bank_afriland.security.request.AuthRequest;
import com.bank_afriland.security.request.AuthResponse;
import com.bank_afriland.security.request.RegisterRequest;
import com.bank_afriland.security.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

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

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthApi {

    private final AuthenticationService authenticateService;

    @PostMapping("/register")
    @ResponseStatus(CREATED)
    public ResponseEntity<CustomResponse> register(@RequestBody RegisterRequest request) throws BadRequestException {
        return new ResponseEntity<>(
                CustomResponse.builder()
                        .data(Map.of("register", authenticateService.create(request)))
                        .status(CREATED)
                        .message("Account created. Check your email to enable your account")
                        .statusCode(CREATED.value())
                        .timeStamp(LocalDateTime.now())
                        .build(), CREATED);
    }

    @PostMapping("/hello-world")
    public ResponseEntity<CustomResponse> helloWorld(@RequestBody  String request) {
        return new ResponseEntity<>(
                CustomResponse.builder()
                        .data(Map.of("register", request))
                        .status(CREATED)
                        .message("test hello")
                        .statusCode(CREATED.value())
                        .timeStamp(LocalDateTime.now())
                        .build(), CREATED);
    }




    @PostMapping("/test/authentication")
    public ResponseEntity<String> loginTest(@RequestBody @Valid AuthRequest request) throws  BadRequestException {
        AuthResponse authenticate = authenticateService.authenticate(request);
        return new ResponseEntity<String>(authenticate.getToken(), OK);
    }


    @PostMapping("/authentication")
    public ResponseEntity<CustomResponse> login(@RequestBody @Valid AuthRequest request) throws  BadRequestException {
        return new ResponseEntity<>(
                CustomResponse.builder()
                        .data(Map.of("authResponse", authenticateService.authenticate(request)))
                        .status(OK)
                        .message("users logged in")
                        .statusCode(OK.value())
                        .timeStamp(LocalDateTime.now())
                        .build(), OK);
    }
}