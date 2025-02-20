package com.bank_afriland.project.model.entity;

import com.bank_afriland.security.model.Users;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.time.LocalDateTime;

/**
 * <blockquote><pre>
 * Author   : @Dountio
 * LinkedIn : @SidofDountio
 * GitHub   : @SidofDountio
 * Version  : V1.0
 * Email    : sidofdountio406@gmail.com
 * Since    : 2/17/25
 * </blockquote></pre>
 */

@Getter
@Setter
@SuperBuilder
@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdDate", "updatedDate"}, allowGetters = true)
public class AuditMetadata {
//    @Id
//    @SequenceGenerator(name = "primary_key_sequence", allocationSize = 0, initialValue = 1, sequenceName = "primary_key_sequence")
//    @GeneratedValue(generator = "primary_key_sequence", strategy = GenerationType.SEQUENCE)
//    @Column(name = "id")
//    private Long id;

    @CreatedBy
    private Users createdBy;

    @CreatedDate
    private Instant createdDate;

    @LastModifiedBy
    @Column(insertable = false)
    private String updatedBy;

    @LastModifiedDate
    @Column(insertable = false)
    private LocalDateTime updatedDate;
}
