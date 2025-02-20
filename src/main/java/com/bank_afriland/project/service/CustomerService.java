package com.bank_afriland.project.service;

import com.bank_afriland.project.mappers.CustomerMapper;
import com.bank_afriland.project.model.Customer;
import com.bank_afriland.project.repo.CustomerRepository;
import com.bank_afriland.project.request.CustomerRequest;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
@Slf4j
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;


    public CustomerService(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;

    }


    public Customer createCustomer(CustomerRequest customer) {
        Optional<Customer> presentCustomer = customerRepository.findByEmail(customer.email());
        if (presentCustomer.isPresent()) {
            throw new IllegalStateException("Customer exist with the provide email");
        }
        Optional<Customer> byPhoneNumber = customerRepository.findByPhoneNumber(customer.phoneNumber());
        if (byPhoneNumber.isPresent()) {
            throw new IllegalStateException("Customer exist with the provide Phone number");
        }
        Customer customerToSave = customerMapper.toCustomer(customer);
        return customerRepository.save(customerToSave);
    }

    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found with ID: " + id));
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer updateCustomer(Long id, Customer customerDetails) {
        Customer customer = getCustomerById(id);
        // Update customer details
        return customerRepository.save(customer);
    }

    public void deleteCustomer(Long id) {
        Customer customer = getCustomerById(id);
        customerRepository.delete(customer);
    }

    public Customer getCustomerByEmail(String customerEmail) {
        return customerRepository.findByEmail(customerEmail).orElseThrow(() -> new EntityNotFoundException("Not customer match with this email  " + customerEmail));
    }
}
