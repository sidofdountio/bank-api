package com.bank_afriland.project.request;

import jakarta.validation.constraints.NotNull;

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

public record BranchRequest(
        @NotNull(message = "Bank code cannot be empty")
        String bankCode,
        @NotNull(message = "Bank code cannot be empty")
        String  branchCode,
        String name,
        String address,
        String email

) {

}
