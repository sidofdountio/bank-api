package com.bank_afriland.project.utils;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.bank_afriland.project.utils.BankCodeGenerator.generateAccountNumber;
import static org.junit.jupiter.api.Assertions.*;

class BankCodeGeneratorTest {

    @BeforeEach
    void setUp() {
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
    void generateRIB() {
    }

    @Test
    public void testGenerateAccountNumberLength() {
        String accountNumber = generateAccountNumber();
        System.out.println(accountNumber);
        assertEquals(11, accountNumber.length(), "Account number should be exactly 11 digits long");
    }
}