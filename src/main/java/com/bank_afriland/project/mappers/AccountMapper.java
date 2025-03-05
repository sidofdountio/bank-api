package com.bank_afriland.project.mappers;

import com.bank_afriland.project.model.Account;
import com.bank_afriland.project.model.Branch;
import com.bank_afriland.project.model.Customer;
import com.bank_afriland.project.model.enumeration.AccountStatus;
import com.bank_afriland.project.model.enumeration.AccountType;
import com.bank_afriland.project.request.AccountRequest;
import com.bank_afriland.project.service.BranchService;
import com.bank_afriland.project.utils.BankCodeGenerator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.bank_afriland.project.utils.BankCodeGenerator.*;

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

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountMapper {
    private final BranchService branchService;
    public Account toAccount(AccountRequest request, Customer customer) {
        Branch branch = branchService.getBranchByCode(request.branchCode());
        String accountNumber = Optional.ofNullable(request.accountNumber())
                .filter(str -> !str.isEmpty())
                .orElseGet(() -> {
                    String generatedNumber = generateAccountNumberNew();
                    log.info("Account number generated {}", generatedNumber);
                    return generatedNumber;
                });

        String rib = Optional.ofNullable(request.rib())
                .filter(str -> !str.isEmpty())
                .orElseGet(() -> {
                    String generatedRib = generateRIB(customer.getBranch().getBank().getBankCode(),
                            customer.getBranch().getBranchCode(), accountNumber);
                    log.info("RIB generated {}", generatedRib);
                    return generatedRib;
                });

        String iban = Optional.ofNullable(request.iban())
                .filter(str -> !str.isEmpty())
                .orElseGet(() -> {
                    String generatedIban = generateIBAN("CM",
                            customer.getBranch().getBank().getBankCode(),
                            customer.getBranch().getBranchCode(), accountNumber);
                    log.info("IBAN generated {}", generatedIban);
                    return generatedIban;
                });

        // Validation checks
        if (accountNumber.isEmpty()) {
            throw new IllegalStateException("Account number generation failed, account number is empty.");
        }
        if (rib.isEmpty()) {
            throw new IllegalStateException("RIB generation failed, RIB is empty.");
        }
        if (iban.isEmpty()) {
            throw new IllegalStateException("IBAN generation failed, IBAN is empty.");
        }

        return Account.builder()
                .status(AccountStatus.ACTIVE)
                .accountType(AccountType.SAVINGS)
                .customer(customer)
                .accountNumber(accountNumber)
                .branch(branch)
                .iban(iban)
                .rib(rib)
                .active(true)
                .createdAt(request.createdAt())
                .balance(request.balance())
                .initialBalance(request.balance())
                .build();
    }
}
