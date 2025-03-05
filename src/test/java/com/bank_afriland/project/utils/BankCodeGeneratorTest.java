package com.bank_afriland.project.utils;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.bank_afriland.project.utils.BankCodeGenerator.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BankCodeGeneratorTest {



    @BeforeEach
    void setUp() {

    }

    @Test
    void shouldGenerateIBAN() {
        String countryCode = "CM";
        String bankCode = "10005";
        String branchCode = "00034";
//        String accountNumber = "0608398100104";
        String accountNumber = generateAccountNumberNew();

        String iban = generateIBAN(countryCode, bankCode, branchCode, accountNumber);
        assertTrue(iban.startsWith("CM"));
//        assertEquals("CM2110005000340608398100104", iban.replace(" ", ""));
        System.out.println("Generated IBAN: " + iban);
    }

    @Test
    void shouldGenerateRIB() {
        String countryCode = "CM";
        String bankCode = "10005";
        String branchCode = "00034";
        String accountNumber = generateAccountNumberNew();
        String RIB = generateRIB(bankCode,branchCode,accountNumber);

        System.out.println("Generated RIB: " + RIB);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void generateBankCode() {
    }

    @Test
    void generateBranchCode() {
    }




    @Test
    public void testGenerateAccountNumberLength() {
        String accountNumber = generateAccountNumberNew();
        System.out.println(accountNumber);
        assertEquals(11, accountNumber.length(), "Account number should be exactly 11 digits long");
    }
}