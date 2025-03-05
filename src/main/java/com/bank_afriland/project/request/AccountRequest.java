package com.bank_afriland.project.request;

import com.bank_afriland.project.model.enumeration.AccountType;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * <blockquote><pre>
 * Author   : @Dountio
 * LinkedIn : @SidofDountio
 * GitHub   : @SidofDountio
 * Version  : V1
 * Licence   : All Right Reserved BIS
 * Since    : 2/18/25
 * </blockquote></pre>
 */

public record AccountRequest(
        AccountType accountType,
        @NotNull(message = "balance should not be null")
        @DecimalMin(value = "0.01", message = "balance should be greater than 0")
        BigDecimal balance,
//        @NotNull(message = "you must select the customer")
        Long customerId,
        @NotNull(message = "you must select the branch")
        String branchCode,
        String rib,
        String iban,
        String accountNumber,
        LocalDate createdAt
) { }
