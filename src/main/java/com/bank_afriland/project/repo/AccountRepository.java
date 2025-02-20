package com.bank_afriland.project.repo;

import com.bank_afriland.project.model.Account;
import com.bank_afriland.project.model.Customer;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * <blockquote><pre>
 * Author   : @Dountio
 * LinkedIn : @SidofDountio
 * GitHub   : @SidofDountio
 * Version  : V1
 * Since    : 2/18/25
 * </blockquote></pre>
 */

public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByCustomer(Customer customer);

    Optional<Account> findByAccountNumber(@NotNull(message = "Account number is required") String accountNumber);
}
