package com.bank_afriland.project.request;

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

public record CustomerRequest(
        String branchCode,
        String firstName,
        String lastName,
        String email,
        String phoneNumber
) {
}
