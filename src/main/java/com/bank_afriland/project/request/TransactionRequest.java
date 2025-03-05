package com.bank_afriland.project.request;

import com.bank_afriland.project.model.enumeration.TransactionType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * <blockquote><pre>
 * Author   : @Dountio
 * LinkedIn : @SidofDountio
 * GitHub   : @SidofDountio
 * Version  : V1
 * Licence   : All Right Reserved BIS
 * Since    : 2/19/25
 * </blockquote></pre>
 */

public record TransactionRequest(
        TransactionType transactionType,
        @NotNull(message = "Amount is required")
        @Positive(message = "49")
        BigDecimal amount,
        @NotNull(message = "50")
        LocalDate createdAt,
        String description,
        BigDecimal debit,
        BigDecimal credit,
        Long sourceAccountId,
        @NotNull(message = "Account number is required")
        @NotEmpty(message = "Account number is required")
        String accountNumber
) {
}
