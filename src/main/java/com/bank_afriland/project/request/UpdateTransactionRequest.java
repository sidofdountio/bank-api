package com.bank_afriland.project.request;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

/**
 * <blockquote><pre>
 * Author   : @Dountio
 * LinkedIn : @SidofDountio
 * GitHub   : @SidofDountio
 * Version  : V1
 * Licence   : All Right Reserved BIS
 * Since    : 2/20/25
 * </blockquote></pre>
 */

public record UpdateTransactionRequest(
        String description,
        LocalDate createAt,
        @NotNull(message = "Must select the id")
        Long transactionId
) {
}
