package com.bank_afriland.project.utils;

import java.security.SecureRandom;

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

public class BankCodeGenerator {

    private static final SecureRandom random = new SecureRandom();

    // Generates a random 5-digit bank code
    public String generateBankCode() {
        return String.format("%05d", random.nextInt(100000));
    }

    // Generates a random 5-digit branch code
    public String generateBranchCode() {
        return String.format("%05d", random.nextInt(100000));
    }

    // Generates a random 11-digit account number
//    public String generateAccountNumber1() {
//        return String.format("%011d", random.nextInt(100000000000L));
//    }

    public static String generateAccountNumber() {
        // Generate a random long between 10^10 (10 billion) and 10^11-1 (just under 100 billion)
        // This ensures we always get an 11-digit number
        long min = 10_000_000_000L;
        long max = 100_000_000_000L - 1;
        long range = max - min + 1;

        // Calculate a random number within our range and add the minimum value
        long accountNum = min + (long) (random.nextDouble() * range);

        return String.format("%011d", accountNum);
    }

    // Generates a RIB based on bank code, branch code, and account number
    public String generateRIB(String bankCode, String branchCode, String accountNumber) {
        // Create the base RIB without the RIB key
        String baseRIB = bankCode + branchCode + accountNumber;

        // Calculate RIB key
        String ribKey = calculateRIBKey(baseRIB);

        // Form the complete RIB
        return baseRIB + ribKey;
    }

    // Generates an IBAN based on country code and bank, branch, and account numbers
    public String generateIBAN(String countryCode, String bankCode, String branchCode, String accountNumber) {
        String baseIban = countryCode + "00" + bankCode + branchCode + accountNumber;
        String checkDigits = calculateCheckDigits(baseIban);
        return baseIban.substring(0, 2) + checkDigits + baseIban.substring(4);
    }

    // Calculates the RIB key using a simple algorithm
    private String calculateRIBKey(String baseRIB) {
        // Use modulo 97 to calculate the key
        long total = Long.parseLong(baseRIB);
        int ribKey = (97 - (int) (total % 97)) % 97;

        return String.format("%02d", ribKey); // Return as a 2-digit string
    }

    public static String generateCustomerCode() {
        SecureRandom secureRandom = new SecureRandom();
        int customerCode = 10000 + secureRandom.nextInt(90000);
        return String.valueOf(customerCode);
    }

    // Calculates check digits for the IBAN
    private String calculateCheckDigits(String iban) {
        // Replace the first two characters with "00"
        String modifiedIban = iban.substring(4) + iban.substring(0, 2);

        // Convert letters to numbers (A=10, B=11, ..., Z=35)
        StringBuilder numericIban = new StringBuilder();
        for (char c : modifiedIban.toCharArray()) {
            if (Character.isDigit(c)) {
                numericIban.append(c);
            } else {
                numericIban.append(c - 'A' + 10); // Convert A-Z to 10-35
            }
        }

        // Calculate check digits using modulo 97
        long remainder = Long.parseLong(numericIban.toString()) % 97;
        int checkDigits = 98 - (int) remainder;

        return String.format("%02d", checkDigits); // Return as 2-digit string
    }


}
