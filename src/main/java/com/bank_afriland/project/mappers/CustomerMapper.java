package com.bank_afriland.project.mappers;

import com.bank_afriland.project.model.Branch;
import com.bank_afriland.project.model.Customer;
import com.bank_afriland.project.request.CustomerRequest;
import com.bank_afriland.project.service.BranchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.bank_afriland.project.utils.BankCodeGenerator.generateCustomerCode;

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
public class CustomerMapper {

 private final BranchService branchService;
 public Customer toCustomer(CustomerRequest request){
  Branch branch = branchService.getBranchByCode(request.branchCode());
  return Customer.builder()
          .branch(branch)
          .customerCode(generateCustomerCode())
          .email(request.email())
          .phoneNumber(request.phoneNumber())
          .firstName(request.firstName())
          .lastName(request.lastName())
          .build();
 }
}
