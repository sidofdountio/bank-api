package com.bank_afriland.project.api;

import com.bank_afriland.project.request.AccountRequest;
import com.bank_afriland.project.service.AccountService;
import com.bank_afriland.reponse.CustomResponse;
import lombok.RequiredArgsConstructor;
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
 * Since    : 2/18/25
 * </blockquote></pre>
 */

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PostMapping("/save")
    @ResponseStatus(CREATED)
    public ResponseEntity<CustomResponse> save(@RequestBody AccountRequest request) {
        return new ResponseEntity<>(
                CustomResponse.builder()
                        .data(Map.of("account", accountService.createNewAccount(request)))
                        .status(CREATED)
                        .message("Account created")
                        .statusCode(CREATED.value())
                        .timeStamp(LocalDateTime.now())
                        .build(), CREATED);
    }

    @GetMapping("/{bankAccountId}")
    @ResponseStatus(OK)
    public ResponseEntity<CustomResponse> getBankAccountById(@PathVariable(name = "bankAccountId") Long bankAccountId) {
        return new ResponseEntity<>(
                CustomResponse.builder()
                        .data(Map.of("account", accountService.getBankAccountById(bankAccountId)))
                        .status(OK)
                        .message("bank account successfully retrieved")
                        .statusCode(OK.value())
                        .timeStamp(LocalDateTime.now())
                        .build(), OK);
    }

    @GetMapping
    @ResponseStatus(OK)
    public ResponseEntity<CustomResponse> getBankAccounts() {
        return new ResponseEntity<>(
                CustomResponse.builder()
                        .data(Map.of("accounts", accountService.getAllBankAccounts()))
                        .status(OK)
                        .message("bank account successfully retrieved")
                        .statusCode(OK.value())
                        .timeStamp(LocalDateTime.now())
                        .build(), OK);
    }

    @GetMapping("/{bankAccountId}/balance")
    @ResponseStatus(OK)
    public ResponseEntity<CustomResponse> getBankAccountBalance(@PathVariable(name = "bankAccountId") Long bankAccountId) {
        return new ResponseEntity<>(
                CustomResponse.builder()
                        .data(Map.of("account", accountService.getBankAccountById(bankAccountId)))
                        .status(OK)
                        .message("bank account successfully retrieved")
                        .statusCode(OK.value())
                        .timeStamp(LocalDateTime.now())
                        .build(), OK);
    }

}
