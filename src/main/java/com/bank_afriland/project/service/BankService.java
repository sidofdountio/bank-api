package com.bank_afriland.project.service;

import com.bank_afriland.project.model.Bank;
import com.bank_afriland.project.repo.BankRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
public class BankService {

    private final BankRepository bankRepository;

    public Bank getBank(Long bankId){
        return bankRepository.findById(bankId).orElseThrow(()-> new EntityNotFoundException("Bank not found"));
    }

    // Update bank details
    @Transactional
    public Bank updateBank(Long id, Bank updatedBank) {
        Bank existingBank = bankRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Bank not found"));

        existingBank.setBankCode(updatedBank.getBankCode());
        existingBank.setName(updatedBank.getName());
        existingBank.setSwiftCode(updatedBank.getSwiftCode());
        existingBank.setHeadOfficeAddress(updatedBank.getHeadOfficeAddress());
        existingBank.setPhoneNumber(updatedBank.getPhoneNumber());
        existingBank.setEmail(updatedBank.getEmail());
        existingBank.setWebsite(updatedBank.getWebsite());

        return bankRepository.save(existingBank);
    }

    public Bank getBankByCode(String bankCode) {
        return bankRepository.findByBankCode(bankCode)
                .orElseThrow(() -> new EntityNotFoundException("Bank not found"));
    }
}
