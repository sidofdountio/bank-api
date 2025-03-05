package com.bank_afriland.project.api;

import com.bank_afriland.project.request.BranchRequest;
import com.bank_afriland.project.service.BranchService;
import com.bank_afriland.reponse.CustomResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static java.time.LocalDateTime.now;
import static java.util.Map.of;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

/**
 * <blockquote><pre>
 * Author   : @Dountio
 * LinkedIn : @SidofDountio
 * GitHub   : @SidofDountio
 * Version  : V1.0
 * Email    : sidofdountio406@gmail.com
 * Licence  : All Right Reserved BIS
 * Since    : 2/23/25
 * </blockquote></pre>
 */

@RestController
@RequestMapping("/branches")
@RequiredArgsConstructor
public class BranchAPI {
    private final BranchService branchService;

    @GetMapping
    public ResponseEntity<CustomResponse> getAllBranches() {
        return new ResponseEntity<>(CustomResponse.builder()
                .timeStamp(now())
                .data(of("branches", branchService.getAllBranches()))
                .message("Branches retrieved")
                .status(OK)
                .statusCode(OK.value())
                .build(), OK);
    }


    @GetMapping("/branch")
    public ResponseEntity<CustomResponse> getBranch(@RequestParam(name = "branchId") Long branchId) {
        return new ResponseEntity<>(CustomResponse.builder()
                .timeStamp(now())
                .data(of("branch", branchService.getBranchById(branchId)))
                .message("Branch retrieved")
                .status(OK)
                .statusCode(OK.value())
                .build(), OK);
    }

    @GetMapping("/branch-code")
    public ResponseEntity<CustomResponse> getBranchCode(@RequestParam(name = "branchCode") String branchCode) {
        return new ResponseEntity<>(CustomResponse.builder()
                .timeStamp(now())
                .data(of("branch", branchService.getBranchByCode(branchCode)))
                .message("Branches retrieved")
                .status(OK)
                .statusCode(OK.value())
                .build(), OK);
    }

    @PostMapping("/save")
    @ResponseStatus(CREATED)
    public ResponseEntity<CustomResponse> create(@Valid @RequestBody BranchRequest  request) {
        return new ResponseEntity<>(CustomResponse.builder()
                .timeStamp(now())
                .data(of("branch", branchService.saveBranch(request)))
                .message("Branches created")
                .status(CREATED)
                .statusCode(CREATED.value())
                .build(), CREATED);
    }
}
