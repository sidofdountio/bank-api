package com.bank_afriland.project.model;

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
public class Bank {
    @Id
    @SequenceGenerator(name = "bank_id_sequence", allocationSize = 1, initialValue = 1, sequenceName = "bank_id_sequence")
    @GeneratedValue(generator = "bank_id_sequence", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false, unique = true)
    private String bankCode;

    @Column(nullable = false, unique = true)
    private String ribKey;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String swiftCode;

    private String headOfficeAddress;
    private String phoneNumber;
    private String email;
    private String website;

    @OneToMany(mappedBy = "bank")
    private List<Branch> branches;
}
