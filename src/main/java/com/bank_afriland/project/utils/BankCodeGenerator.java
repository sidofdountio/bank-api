package com.bank_afriland.project.utils;

import lombok.extern.slf4j.Slf4j;

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
@Slf4j
public class BankCodeGenerator {

    private static final SecureRandom random = new SecureRandom();


    public static String generateAccountNumberNew() {
        StringBuilder accountNumber = new StringBuilder();

        // Generate 10 random digits
        for (int i = 0; i < 10; i++) {
            accountNumber.append(random.nextInt(10));
        }

        // Calculate and append a check digit
        int checkDigit = calculateCheckDigit(accountNumber.toString());
        accountNumber.append(checkDigit);

        log.info("Final Account number generated with 11 {}", accountNumber);
        return accountNumber.toString();
    }


    // Generates a RIB based on bank code, branch code, and account number
    public static String generateRIB(String bankCode, String branchCode, String accountNumber) {
        // Create the base RIB without the RIB key
        String baseRIB = bankCode + branchCode + accountNumber;

        // Calculate RIB key
//        String ribKey = calculateRIBKey(baseRIB);
        String ribKey = "04";

        // Form the complete RIB
        return baseRIB + ribKey;
    }

    // Generates an IBAN based on country code and bank, branch, and account numbers
    public static String generateIBAN(String countryCode, String bankCode, String branchCode, String accountNumber) {
        String baseIban = countryCode + "00" + bankCode + branchCode + accountNumber;
//        String checkDigits = calculateCheckDigits(baseIban);
        String checkDigits = "21";
        String IBAN = countryCode + checkDigits + bankCode + branchCode + accountNumber;
        log.info("IBAN generated {}", IBAN);
        return IBAN;
    }

    // Generates a random 5-digit bank code
    public String generateBankCode() {
        return String.format("%05d", random.nextInt(100000));
    }

    // Generates a random 5-digit branch code
    public String generateBranchCode() {
        return String.format("%05d", random.nextInt(100000));
    }


    public static String generateAccountNumber() {
        // Generate a random long between 10^10 (10 billion) and 10^11-1 (just under 100 billion)
        // This ensures we always get an 11-digit number
        long min = 10_000_000_000L;
        long max = 100_000_000_000L - 1;
        long range = max - min + 1;

        // Calculate a random number within our range and add the minimum value
        long accountNum = min + (long) (random.nextDouble() * range);

        String accountNumber = String.format("%011d", accountNum);
        log.info("Account number generated {}", accountNumber);
        return accountNumber;
    }

    /**
     * Calculate a check digit using Luhn algorithm for validation
     *
     * @param number The account number without check digit
     * @return The check digit (0-9)
     */

    private static int calculateCheckDigit(String number) {
        int sum = 0;
        boolean alternate = false;

        // Process from right to left
        for (int i = number.length() - 1; i >= 0; i--) {
            int digit = Character.getNumericValue(number.charAt(i));

            if (alternate) {
                digit *= 2;
                if (digit > 9) {
                    digit -= 9;
                }
            }

            sum += digit;
            alternate = !alternate;
        }

        // The check digit is what needs to be added to make the sum divisible by 10
        return (10 - (sum % 10)) % 10;
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


    // Calculates check digits for the IBAN using BigInteger for larger numbers
    private String calculateCheckDigits(String iban) {
        // Move the four initial characters to the end
        String modifiedIban = iban.substring(4) + iban.substring(0, 4);

        // Convert letters to numbers (A=10, B=11, ..., Z=35)
        StringBuilder numericIban = new StringBuilder();
        for (char c : modifiedIban.toCharArray()) {
            if (Character.isDigit(c)) {
                numericIban.append(c);
            } else {
                numericIban.append(c - 'A' + 10); // Convert A-Z to 10-35
            }
        }

        // Calculate check digits using modulo 97 with BigInteger
        java.math.BigInteger numericValue = new java.math.BigInteger(numericIban.toString());
        java.math.BigInteger ninetySeven = new java.math.BigInteger("97");
        java.math.BigInteger remainder = numericValue.remainder(ninetySeven);

        // Check digits are 98 minus the remainder
        int checkDigits = 98 - remainder.intValue();

        return String.format("%02d", checkDigits); // Return as 2-digit string
    }


}
