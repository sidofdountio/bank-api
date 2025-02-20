package com.bank_afriland.project.mappers;

import com.bank_afriland.project.model.Bank;
import com.bank_afriland.project.model.Branch;
import com.bank_afriland.project.request.BranchRequest;
import com.bank_afriland.project.service.BankService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
public class BranchMapper {

    private final BankService bankService;

    public Branch  toBranch(BranchRequest request,String bankCode){
        Bank bank = bankService.getBankByCode(bankCode);
        return Branch.builder()
                .branchCode(request.branchCode())
                .email(request.email())
                .bank(bank)
                .address(request.address())
                .name(request.name())
                .build();
    }
}
