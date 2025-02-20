package com.bank_afriland.project.repo;

import com.bank_afriland.project.model.Bank;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * <blockquote><pre>
 * Author   : @Dountio
 * LinkedIn : @SidofDountio
 * GitHub   : @SidofDountio
 * Version  : V1
 * Licence   : All Right Reserved BIS
 * Since    : 2/18/25
 * </blockquote></pre>
 */

public interface BankRepository extends JpaRepository<Bank,Long> {
 Optional<Bank> findByBankCode(String bankCode);
}
