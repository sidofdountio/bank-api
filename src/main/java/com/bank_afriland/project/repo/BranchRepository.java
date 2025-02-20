package com.bank_afriland.project.repo;

import com.bank_afriland.project.model.Branch;
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

public interface BranchRepository extends JpaRepository<Branch,Long> {
    Optional<Branch> findByBranchCode(String branchCode);
}
