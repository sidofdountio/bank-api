package com.bank_afriland.project.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

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

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Branch {
    @Id
    @SequenceGenerator(name = "branch_id_sequence", allocationSize = 1, initialValue = 1, sequenceName = "branch_id_sequence")
    @GeneratedValue(generator = "branch_id_sequence", strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "bank_id", nullable = false)
    private Bank bank;

    @Column(nullable = false, unique = true)
    private String branchCode;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String address;

    private String phoneNumber;
    private String email;
    private boolean isActive = true;

    @JsonIgnore
    @OneToMany(mappedBy = "branch")
    private List<Account> accounts;
    @JsonIgnore
    @OneToMany(mappedBy = "branch")
    private List<Customer> customers;

//    @OneToMany(mappedBy = "branch")
//    private List<BankStaff> staff;





}
