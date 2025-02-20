package com.bank_afriland.security.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

/**
 * <blockquote><pre>
 * Author   : @Dountio
 * LinkedIn : @SidofDountio
 * GitHub   : @SidofDountio
 * Version  : V1.0
 * Email    : sidofdountio406@gmail.com
 * Licence  : All Right Reserved BIS
 * Since    : 2/16/25
 * </blockquote></pre>
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@SuperBuilder
@Table(
        name = "token",
        uniqueConstraints = @UniqueConstraint(columnNames = "token", name = "UQ_Token_token")
)
public class Token {
    @Id
    @SequenceGenerator(name = "token_id_sequence", allocationSize = 1, initialValue = 1, sequenceName = "token_id_sequence")
    @GeneratedValue(generator = "token_id_sequence", strategy = GenerationType.SEQUENCE)
    private Long id;

//    @Column(columnDefinition = "TEXT")
    private String token;

    private String refreshToken;

    private LocalDateTime createAt;

    private LocalDateTime expireAt;

    private boolean expired;
    private boolean revoked;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, referencedColumnName = "user_id", foreignKey = @ForeignKey(name = "fk_token_user"))
    private Users user;

}
