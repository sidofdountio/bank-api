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

public record BranchRequest(
        Long bankId,
        String  branchCode,
        String name,
        String address,
        String email

) {

}
