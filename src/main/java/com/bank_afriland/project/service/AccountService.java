package com.bank_afriland.project.service;

import com.bank_afriland.project.mappers.AccountMapper;
import com.bank_afriland.project.model.Account;
import com.bank_afriland.project.model.Customer;
import com.bank_afriland.project.repo.AccountRepository;
import com.bank_afriland.project.request.AccountRequest;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <blockquote><pre>
 * Author   : @Dountio
 * LinkedIn : @SidofDountio
 * GitHub   : @SidofDountio
 * Version  : V1.0
 * Email    : sidofdountio406@gmail.com
 * Since    : 2/18/25
 * </blockquote></pre>
 */

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountService {
    private final AccountMapper accountMapper;
    private final BankService bankService;
    private final CustomerService customerService;
    private final AccountRepository accountRepository;


    public Account createNewAccount(AccountRequest request) {
        Customer customerById = customerService.getCustomerById(request.customerId());
        Account accountToSave = accountMapper.toAccount(request, customerById);
        log.info("saving new account {}", accountToSave);
        return accountRepository.save(accountToSave);
//        return accountToSave;
    }

    public Account getBankAccountByCustomer(String customerEmail){
        Customer customer=  customerService.getCustomerByEmail(customerEmail);
        return accountRepository.findByCustomer(customer).orElseThrow(()-> new EntityNotFoundException(""));
    }

    // Retrieve a BankAccount by its ID
    public Account getBankAccountById(Long accountId) {
        log.info("Fetch bank account by {}", accountId);
        return accountRepository.findById( accountId).orElseThrow(() -> new EntityNotFoundException("Account not found")); // Fetch account details
    }

    // Retrieve all BankAccounts
    public List<Account> getAllBankAccounts() {
        return accountRepository.findAll(); // Fetch all accounts
    }


    public void updateTransactionChange(Account account){
        accountRepository.save(account);
    }


    public Account getByAccountNumber(@NotNull(message = "Account number is required") String accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber).orElseThrow(()-> new EntityNotFoundException("No account match with this account number"));
    }
}
