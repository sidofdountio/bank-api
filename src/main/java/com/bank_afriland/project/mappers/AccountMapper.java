package com.bank_afriland.project.mappers;

import com.bank_afriland.project.model.Account;
import com.bank_afriland.project.model.Branch;
import com.bank_afriland.project.model.Customer;
import com.bank_afriland.project.model.enumeration.AccountType;
import com.bank_afriland.project.request.AccountRequest;
import com.bank_afriland.project.service.BranchService;
import com.bank_afriland.project.utils.BankCodeGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.bank_afriland.project.model.enumeration.AccountStatus.ACTIVE;
import static com.bank_afriland.project.utils.BankCodeGenerator.generateAccountNumber;

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
public class AccountMapper {
    private final BranchService branchService;
    private  BankCodeGenerator bankCodeGenerator;

    public Account toAccount(AccountRequest request, Customer customer) {
        Branch branch = branchService.getBranchByCode(request.branchCode());
        String rib = request.rib();
        String iban = request.iban();
        String accountNumber = request.accountNumber();

        if (accountNumber == null) {
            accountNumber = generateAccountNumber();
        }
        if (rib == null) {
            rib = bankCodeGenerator.generateRIB(customer.getBranch().getBank().getBankCode(), customer.getBranch().getBranchCode(), accountNumber);
        }
        if (iban == null) {
            iban = bankCodeGenerator.generateIBAN("CM", customer.getBranch().getBank().getBankCode(), customer.getBranch().getBranchCode(), accountNumber);
        }

        if (accountNumber.length() < 11) {
            throw new IllegalStateException("Account number not in right format");
        }

        return Account.builder()
                .status(ACTIVE)
                .accountType(AccountType.SAVINGS)
                .customer(customer)
                .accountNumber(accountNumber)
                .branch(branch)
                .iban(iban)
                .rib(rib)
                .balance(request.balance())
                .initialBalance(request.balance())
                .build();
    }
}
