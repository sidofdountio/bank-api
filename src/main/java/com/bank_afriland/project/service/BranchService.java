package com.bank_afriland.project.service;

import com.bank_afriland.project.mappers.BranchMapper;
import com.bank_afriland.project.model.Bank;
import com.bank_afriland.project.model.Branch;
import com.bank_afriland.project.repo.BranchRepository;
import com.bank_afriland.project.request.BranchRequest;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
@Transactional
public class BranchService {
    private final BranchRepository branchRepository;
    private final BankService bankService;
    private final BranchMapper  branchMapper;


    public Branch createBranch(BranchRequest request, String bankCode) {
        Bank bank = bankService.getBankByCode(bankCode);
        Branch branch = branchMapper.toBranch(request, bankCode);
        return branchRepository.save(branch);
    }

    public Branch getBranchByCode(String branchCode) {
        return branchRepository.findByBranchCode(branchCode)
                .orElseThrow(() -> new EntityNotFoundException("Branch not found"));
    }

    public Branch getBranchById(Long branchId) {
        return branchRepository.findById(branchId)
                .orElseThrow(() -> new EntityNotFoundException("Branch not found"));
    }

}
