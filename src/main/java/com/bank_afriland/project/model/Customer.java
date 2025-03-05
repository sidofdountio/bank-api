package com.bank_afriland.project.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
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
public class Customer {
    @Id
    @SequenceGenerator(name = "sequence_customer_id", allocationSize = 1, initialValue = 1, sequenceName = "sequence_customer_id")
    @GeneratedValue(generator = "sequence_customer_id", strategy = GenerationType.SEQUENCE)
    private Long customerId;

    @Column(name = "first_name")
    @NotEmpty(message = "first name cannot be empty")
    @NotNull(message = "first name cannot be null")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(nullable = false,unique = true)
    private String customerCode;

    @Column(nullable = false,unique = true)
    private String email;
    private String phoneNumber;
    private String address;
    private LocalDate dateOfBirth;
    private boolean hasAccount;

    private String secondFirstName;
    private String secondLastName;


//    @Column(nullable = false)
//    private String identityType;
//
//    @Column(nullable = false)
//    private String identityNumber;

//    @Temporal(TemporalType.DATE)
//    private Date dateOfBirth;

    @ManyToOne
    @JoinColumn(name = "branch_id", nullable = false)
    private Branch branch;

    @JsonIgnore
    @OneToMany(mappedBy = "customer")
    private List<Account> accounts;



}
