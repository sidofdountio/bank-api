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


public class TransactionReferenceGenerator {
    public static String generateReference() {
        // Get current date components
        LocalDateTime now = LocalDateTime.now();
        String year = String.valueOf(now.getYear());
        String month = String.format("%02d", now.getMonthValue());
        String day = String.format("%02d", now.getDayOfMonth());

        // Generate a random sequence number (5 digits)
        int sequenceNumber = (int) (Math.random() * 99999);
        String sequence = String.format("%05d", sequenceNumber);

        // Combine all parts
        return "VIR-" + year + month + day + sequence;
    }

    public static String generateReferenceWithdrawal() {
        // Get current date components
        LocalDateTime now = LocalDateTime.now();
        String year = String.valueOf(now.getYear());
        String month = String.format("%02d", now.getMonthValue());
        String day = String.format("%02d", now.getDayOfMonth());

        // Generate a random sequence number (5 digits)
        int sequenceNumber = (int) (Math.random() * 99999);
        String sequence = String.format("%05d", sequenceNumber);

        // Combine all parts
        return "WIT-" + year + month + day + sequence;
    }

    public static String generateReferenceDeposit() {
        // Get current date components
        LocalDateTime now = LocalDateTime.now();
        String year = String.valueOf(now.getYear());
        String month = String.format("%02d", now.getMonthValue());
        String day = String.format("%02d", now.getDayOfMonth());

        // Generate a random sequence number (5 digits)
        int sequenceNumber = (int) (Math.random() * 99999);
        String sequence = String.format("%05d", sequenceNumber);

        // Combine all parts
        return "TRX-" + year + month + day + sequence;
    }
}
