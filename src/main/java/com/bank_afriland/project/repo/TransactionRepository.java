package com.bank_afriland.project.repo;

import com.bank_afriland.project.model.Account;
import com.bank_afriland.project.model.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * <blockquote><pre>
 * Author   : @Dountio
 * LinkedIn : @SidofDountio
 * GitHub   : @SidofDountio
 * Version  : V1
 * Licence   : All Right Reserved BIS
 * Since    : 2/19/25
 * </blockquote></pre>
 */

public interface TransactionRepository extends JpaRepository<Transaction,Long>, PagingAndSortingRepository<Transaction,Long> {
    @Query("SELECT t FROM Transaction t WHERE t.account = :account")
    Page<Transaction> findAllTransactionBySourceAccount(Pageable pageable, Account account);
    @Query("SELECT t FROM Transaction t ORDER BY t.createdAt ASC")
    List<Transaction> findAllTransactionsOrderByCreatedAtAsc();
}
