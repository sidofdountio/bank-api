package com.bank_afriland.security.repository;

import com.bank_afriland.security.model.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

/**
 * <blockquote><pre>
 * Author   : @Dountio
 * LinkedIn : @SidofDountio
 * GitHub   : @SidofDountio
 * Version  : V1
 * Licence   : All Right Reserved BIS
 * Since    : 2/16/25
 * </blockquote></pre>
 */

public interface TokenRepository extends JpaRepository<Token,Long> {
    Optional<Token>findByToken(String token);
    @Query("""
            SELECT t FROM Token t inner join Users u ON t.user.userId = u.userId
             WHERE u.userId = :id AND (t.expired = false OR t.revoked = false)
            """)
    List<Token> findAllValidTokensByUser(Long id);
}
