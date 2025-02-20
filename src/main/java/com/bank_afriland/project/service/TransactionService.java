package com.bank_afriland.project.service;

import com.bank_afriland.handler.InsufficientBalanceException;
import com.bank_afriland.handler.InvalidAccountStateException;
import com.bank_afriland.project.model.Account;
import com.bank_afriland.project.model.Transaction;
import com.bank_afriland.project.model.enumeration.TransactionStatus;
import com.bank_afriland.project.model.enumeration.TransactionType;
import com.bank_afriland.project.repo.TransactionRepository;
import com.bank_afriland.project.request.TransactionRequest;
import com.bank_afriland.project.request.UpdateTransactionRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

import static com.bank_afriland.project.model.enumeration.TransactionType.ATM_WITHDRAWAL;
import static com.bank_afriland.project.model.enumeration.TransactionType.CASH_WITHDRAWAL;
import static com.bank_afriland.project.utils.TransactionReferenceGenerator.*;

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

@Service
@RequiredArgsConstructor
@Slf4j
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final AccountService accountService;
    private final BigDecimal MIN_BALANCE_REQUIRED = new BigDecimal("100000.00");

    public List<Transaction> getAllTransactionOrderByDate() {
        return transactionRepository.findAllTransactionsOrderByCreatedAtAsc();
    }


    public Page<Transaction> getTransactionWithPagination(Long accountId, int page, int size) {
        Account bankAccountById = accountService.getBankAccountById(accountId);
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").ascending());
        return transactionRepository.findAllTransactionBySourceAccount(pageable, bankAccountById);
    }

    public Transaction getTransaction(Long transactionId) {
        return transactionRepository.findById(transactionId)
                .orElseThrow(() -> new IllegalStateException("We cannot find the  transaction " + transactionId));
    }

    public void updateTransactionDescription(Long transactionId, UpdateTransactionRequest request) {
        Transaction transactionToUpdate = getTransaction(transactionId);
        transactionToUpdate.setDescription(request.description());
        transactionToUpdate.setCreatedAt(request.createAt());
        log.info("transaction updated");
        transactionRepository.save(transactionToUpdate);

    }

    public Transaction makeDeposit(TransactionRequest request) {

        // 0.
        validateWeekendTransaction(request.createdAt());
        // 1. Find and validate account
        Account account = accountService.getByAccountNumber(request.accountNumber());
        // 2. Validate account status
        if (!account.isActive()) {
            throw new InvalidAccountStateException("Account is not active");
        }

        // 3. Create transaction record
        Transaction transaction = Transaction.builder()
                .transactionReference(generateReferenceDeposit())
                .createdAt(request.createdAt())
                .processedAt(request.createdAt().plusDays(1))
                .status(TransactionStatus.PENDING)
                .description(request.description() != null ?
                        request.description() : "Deposit to account")
                .amount(request.amount())
                .type(TransactionType.DEPOSIT)
                .credit(request.amount())
                .debit(BigDecimal.valueOf(0))
                .account(account)
                .balanceAfterTransaction(account.getBalance().add(request.amount()))
                .build();

        // 4. Save initial transaction
        transaction = transactionRepository.save(transaction);

        // 5. Update account balance
        account.setBalance(account.getBalance().add(request.amount()));
        accountService.updateTransactionChange(account);

        // 6. Complete transaction
        transaction.setStatus(TransactionStatus.COMPLETED);
        transaction.setProcessedAt(LocalDate.now());


        return transactionRepository.save(transaction);
    }


    public Transaction makeWithdrawal(TransactionRequest request) {

        //        0. validation date
        validateWeekendTransaction(request.createdAt());

        if (request.transactionType() == null) {
            throw new IllegalStateException("Invalid withdrawal type");
        }
        // 1. Validate account
        Account account = accountService.getByAccountNumber(request.accountNumber());

        // 2. Validate account status
        if (!account.isActive()) {
            throw new InvalidAccountStateException("Account is not active");
        }

        // 4. Check sufficient balance
        BigDecimal finalBalance = account.getBalance().subtract(request.amount());
        if (finalBalance.compareTo(MIN_BALANCE_REQUIRED) < 0) {
            throw new InsufficientBalanceException("Withdrawal would put account below minimum balance");
        }

        // 5. Create transaction record
        Transaction transaction = Transaction.builder()
                .transactionReference(generateReferenceWithdrawal())
                .createdAt(request.createdAt())
                .processedAt(request.createdAt().plusDays(1))
                .status(TransactionStatus.PENDING)
                .description((request.description()))
                .amount(request.amount())
                .debit(request.amount())
                .credit(BigDecimal.valueOf(0))
                .type(CASH_WITHDRAWAL)
                .account(account)
                .balanceAfterTransaction(finalBalance)
                .build();
        // 6. Save initial transaction
        transaction = transactionRepository.save(transaction);

        // 7. Update account balance
        account.setBalance(finalBalance);
        accountService.updateTransactionChange(account);
        // 8. Complete transaction
        transaction.setStatus(TransactionStatus.COMPLETED);

        return transactionRepository.save(transaction);
    }


    public Transaction makeWithdrawalATM(TransactionRequest request) {

        //        0. validation date
        validateWeekendTransaction(request.createdAt());

        if (request.transactionType() == null) {
            throw new IllegalStateException("Invalid withdrawal type");
        }
        // 1. Validate account
        Account account = accountService.getByAccountNumber(request.accountNumber());

        // 2. Validate account status
        if (!account.isActive()) {
            throw new InvalidAccountStateException("Account is not active");
        }

        // 4. Check sufficient balance
        BigDecimal finalBalance = account.getBalance().subtract(request.amount());
        if (finalBalance.compareTo(MIN_BALANCE_REQUIRED) < 0) {
            throw new InsufficientBalanceException("Withdrawal would put account below minimum balance");
        }

        // 5. Create transaction record
        Transaction transaction = Transaction.builder()
                .transactionReference(generateReferenceWithdrawal())
                .createdAt(request.createdAt())
                .processedAt(request.createdAt())
                .status(TransactionStatus.PENDING)
                .description((request.description()))
                .amount(request.amount())
                .debit(request.amount())
                .credit(BigDecimal.valueOf(0))
                .type(ATM_WITHDRAWAL)
                .account(account)
                .balanceAfterTransaction(finalBalance)
                .build();
        // 6. Save initial transaction
        transaction = transactionRepository.save(transaction);

        // 7. Update account balance
        account.setBalance(finalBalance);
        accountService.updateTransactionChange(account);
        // 8. Complete transaction
        transaction.setStatus(TransactionStatus.COMPLETED);

        return transactionRepository.save(transaction);
    }


    public Transaction transferWithdrawal(TransactionRequest request) {

        // 0. valid week day
        validateWeekendTransaction(request.createdAt());
        // 1. Find and validate account
        Account account = accountService.getByAccountNumber(request.accountNumber());

        // Create transaction record
        Transaction transaction = Transaction.builder()
                .transactionReference(generateReference())
                .createdAt(request.createdAt())
                .status(TransactionStatus.PENDING)
                .type(TransactionType.TRANSFER_OUT)
                .amount(request.amount())
                .description(request.description())
                .account(account)
                .destinationAccount(null)
                .balanceAfterTransaction(account.getBalance().subtract(request.amount()))
                .build();


        // Update account balances
        account.setBalance(account.getBalance().subtract(request.amount()));
        // destinationAccount.setBalance(destinationAccount.getBalance().add(request.getAmount()));


        // Save everything
        accountService.updateTransactionChange(account);
//        accountRepository.save(destinationAccount);
        transaction.setStatus(TransactionStatus.COMPLETED);
        transaction.setProcessedAt(request.createdAt().plusDays(1));

        return transactionRepository.save(transaction);

    }


    public Transaction transferDeposit(TransactionRequest request) {

        // 0. valid week day
        validateWeekendTransaction(request.createdAt());
        // 1. Find and validate account
        Account account = accountService.getByAccountNumber(request.accountNumber());

        // Create transaction record
        Transaction transaction = Transaction.builder()
                .transactionReference(generateReference())
                .createdAt(request.createdAt())
                .status(TransactionStatus.PENDING)
                .type(TransactionType.TRANSFER_INCOMING)
                .amount(request.amount())
                .description(request.description())
                .account(account)
                .destinationAccount(null)
                .balanceAfterTransaction(account.getBalance().add(request.amount()))
                .build();

        // Update account balances
        account.setBalance(account.getBalance().add(request.amount()));
        // destinationAccount.setBalance(destinationAccount.getBalance().add(request.getAmount()));

        // Save everything
        accountService.updateTransactionChange(account);
        //        accountRepository.save(destinationAccount);
        transaction.setStatus(TransactionStatus.COMPLETED);
        transaction.setProcessedAt(request.createdAt().plusDays(1));

        return transactionRepository.save(transaction);
    }


    /**
     * TAX WITHDRAWAL
     **/
    public Transaction makeWithdrawalTAX(TransactionRequest request) {

        //        0. validation date
        validateWeekendTransaction(request.createdAt());

        if (request.transactionType() == null) {
            throw new IllegalStateException("Invalid withdrawal type");
        }
        // 1. Validate account
        Account account = accountService.getByAccountNumber(request.accountNumber());

        // 2. Validate account status
        if (!account.isActive()) {
            throw new InvalidAccountStateException("Account is not active");
        }

        // 4. Check sufficient balance
        BigDecimal finalBalance = account.getBalance().subtract(request.amount());
        if (finalBalance.compareTo(MIN_BALANCE_REQUIRED) < 0) {
            throw new InsufficientBalanceException("Withdrawal would put account below minimum balance");
        }

        // 5. Create transaction record
        Transaction transaction = Transaction.builder()
                .transactionReference(generateReferenceWithdrawal())
                .createdAt(request.createdAt())
                .processedAt(request.createdAt().plusDays(1))
                .status(TransactionStatus.PENDING)
                .description((request.description()))
                .amount(request.amount())
                .debit(request.amount())
                .credit(BigDecimal.valueOf(0))
                .type(ATM_WITHDRAWAL)
                .account(account)
                .balanceAfterTransaction(finalBalance)
                .build();
        // 6. Save initial transaction
        transaction = transactionRepository.save(transaction);

        // 7. Update account balance
        account.setBalance(finalBalance);
        accountService.updateTransactionChange(account);
        // 8. Complete transaction
        transaction.setStatus(TransactionStatus.COMPLETED);
        return transactionRepository.save(transaction);
    }


    private void validateWeekendTransaction(LocalDate transactionDate) {
        log.info("Checking valid date {}", transactionDate);
        DayOfWeek dayOfWeek = transactionDate.getDayOfWeek();
        if (dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY) {
            throw new IllegalStateException("Transactions are not allowed on weekends");
        }
    }
}
