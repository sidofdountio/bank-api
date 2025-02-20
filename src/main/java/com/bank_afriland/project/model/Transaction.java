package com.bank_afriland.project.model;

import com.bank_afriland.project.model.enumeration.TransactionStatus;
import com.bank_afriland.project.model.enumeration.TransactionType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.LocalDate;

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

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Transaction {
    @Id
    @SequenceGenerator(name = "transaction_id_sequence", allocationSize = 1, initialValue = 1, sequenceName = "transaction_id_sequence")
    @GeneratedValue(generator = "transaction_id_sequence", strategy = GenerationType.SEQUENCE)
    private Long transactionId;

    @Column(nullable = false, unique = true)
    private String transactionReference;

    @Column(nullable = false)
    @NotNull(message = "createdAt cannot be null")
    private LocalDate createdAt;
    private LocalDate processedAt;

//    @Column(nullable = false)
//    private String currency;

    @Enumerated(EnumType.STRING)
    private TransactionStatus status;

    private String description;
    private String reference;

    private BigDecimal debit;
    private BigDecimal credit;

    @Column(nullable = false, precision = 19, scale = 4)
    private BigDecimal amount;

    @Column(nullable = false, precision = 19, scale = 4)
    private BigDecimal balanceAfterTransaction;

    @Enumerated(EnumType.STRING)
    private TransactionType type;

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    @ManyToOne
    private Account destinationAccount;
}
