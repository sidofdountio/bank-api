package com.bank_afriland.project.api;

import com.bank_afriland.project.service.TransactionService;
import com.bank_afriland.reponse.CustomResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;

import static org.springframework.http.HttpStatus.OK;

/**
 * <blockquote><pre>
 * Author   : @Dountio
 * LinkedIn : @SidofDountio
 * GitHub   : @SidofDountio
 * Version  : V1.0
 * Email    : sidofdountio406@gmail.com
 * Since    : 2/19/25
 * </blockquote></pre>
 */

@RestController
@RequestMapping("/transactions")
@RequiredArgsConstructor
public class TransactionAPI {

    private final TransactionService transactionService;


    @GetMapping
    @ResponseStatus(OK)
    public ResponseEntity<CustomResponse> getTransactions() {
        return new ResponseEntity<>(CustomResponse.builder()
                .data(Map.of("transaction", transactionService.getAllTransactionOrderByDate()))
                .timeStamp(LocalDateTime.now())
                .statusCode(OK.value())
                .status(OK)
                .message("Transaction successfully retrieved")
                .build(), OK);
    }

    @GetMapping("/transaction-page")
    public ResponseEntity<CustomResponse> getTransactionsPage(@RequestParam(name = "accountId") Long accountId,
                                                              @RequestParam(name = "page", defaultValue = "0", required = false) int page,
                                                              @RequestParam(name = "size", defaultValue = "10", required = false) int size) {
        return new ResponseEntity<>(CustomResponse.builder()
                .data(Map.of("page", transactionService.getTransactionWithPagination(accountId, page, size)))
                .timeStamp(LocalDateTime.now())
                .statusCode(OK.value())
                .status(OK)
                .message("Transaction successfully retrieved")
                .build(), OK);
    }

}
