package com.bank_afriland.project.api;

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

import com.bank_afriland.project.request.CustomerRequest;
import com.bank_afriland.project.service.CustomerService;
import com.bank_afriland.reponse.CustomResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static java.time.LocalDateTime.now;
import static java.util.Map.of;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerApi {

    private final CustomerService customerService;

    //
    @GetMapping
    public ResponseEntity<CustomResponse> getCustomers() {
        return new ResponseEntity<>(CustomResponse.builder()
                .timeStamp(now())
                .data(of("customers", customerService.getAllCustomers()))
                .message("Users retrieved")
                .status(OK)
                .statusCode(OK.value())
                .build(), OK);
    }

    @GetMapping("/customer")
    public ResponseEntity<CustomResponse> getCustomerByiD(@RequestParam(name = "customerId") Long customerId) {
        return new ResponseEntity<>(CustomResponse.builder()
                .timeStamp(now())
                .data(of("customer", customerService.getCustomerById(customerId)))
                .message("Users retrieved")
                .status(OK)
                .statusCode(OK.value())
                .build(), OK);
    }

    @PostMapping("/save")
    public ResponseEntity<CustomResponse> saveNewCustomer(@Valid @RequestBody CustomerRequest request) {
        return new ResponseEntity<>(CustomResponse.builder()
                .timeStamp(now())
                .data(of("customer", customerService.createCustomer(request)))
                .message("customer retrieved")
                .status(CREATED)
                .statusCode(CREATED.value())
                .build(), CREATED);
    }
}
