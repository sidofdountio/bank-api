package com.bank_afriland.project.utils;

import java.time.LocalDateTime;

/**
 * <blockquote><pre>
 * Author   : @Dountio
 * LinkedIn : @SidofDountio
 * GitHub   : @SidofDountio
 * Version  : V1.0
 * Email    : sidofdountio406@gmail.com
 * Licence  : All Right Reserved BIS
 * Since    : 2/19/25
 * </blockquote></pre>
 */


public class WithdrawalReferenceGenerator {
    public static String generateWithdrawalReference() {
        // Get current date and time
        LocalDateTime now = LocalDateTime.now();

        // Format date components
        String year = String.valueOf(now.getYear());
        String month = String.format("%02d", now.getMonthValue());
        String day = String.format("%02d", now.getDayOfMonth());

        // Generate sequence number (5 digits)
        int sequenceNumber = (int) (Math.random() * 99999);
        String sequence = String.format("%05d", sequenceNumber);

        // Build reference with prefix RTB (Retrait Bancaire)
        return String.format("RTB%s%s%s%s", year, month, day, sequence);
    }

    // Optional: Generate with custom sequence for testing
    public static String generateWithdrawalReference(int customSequence) {
        LocalDateTime now = LocalDateTime.now();
        String year = String.valueOf(now.getYear());
        String month = String.format("%02d", now.getMonthValue());
        String day = String.format("%02d", now.getDayOfMonth());
        String sequence = String.format("%05d", customSequence);

        return String.format("RTB%s%s%s%s", year, month, day, sequence);
    }
}
