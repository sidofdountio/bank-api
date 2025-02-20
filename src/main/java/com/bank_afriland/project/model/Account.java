package com.bank_afriland.project.model;

import com.bank_afriland.project.model.entity.AuditMetadata;
import com.bank_afriland.project.model.enumeration.AccountStatus;
import com.bank_afriland.project.model.enumeration.AccountType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.List;

/**
 * <blockquote><pre>
 * Author   : @Dountio
 * LinkedIn : @SidofDountio
 * GitHub   : @SidofDountio
 * Version  : V1.0
 * Email    : sidofdountio406@gmail.com
 * Licence  : All Right Reserved BIS
 * Since    : 2/17/25
 * </blockquote></pre>
 */

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Account extends AuditMetadata {
    @Id
    @SequenceGenerator(name = "account_id_sequence", allocationSize = 1, initialValue = 1, sequenceName = "account_id_sequence")
    @GeneratedValue(generator = "account_id_sequence", strategy = GenerationType.SEQUENCE)
    private Long accountId;

    @Column(nullable = false, unique = true)
    private String accountNumber;


    @Column(nullable = false, precision = 19, scale = 4)
    private BigDecimal balance;
    @Column(nullable = false, precision = 19, scale = 4)
    private BigDecimal initialBalance;

    @Enumerated(EnumType.STRING)
    private AccountStatus status;

    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    @Column(nullable = false, unique = true)
    private String rib; // RIB specific to this acc

    @Column(nullable = false, unique = true)
    private String iban; // IBAN specific to this account

    @ManyToOne
    @JoinColumn(name = "branch_id", nullable = false)
    private Branch branch;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;


//    @Column(nullable = false)
//    private String currency;

    @Column(precision = 19, scale = 4)
    private BigDecimal dailyTransactionLimit;

    @JsonIgnore
    @OneToMany(mappedBy = "account")
    private List<Transaction> transactions;

    private boolean active =true;

}
