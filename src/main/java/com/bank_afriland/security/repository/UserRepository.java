package com.bank_afriland.security.repository;

import com.bank_afriland.security.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

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

public interface UserRepository extends JpaRepository<Users, Long> {
    @Query("" +
            "SELECT CASE WHEN COUNT(u) > 0 THEN " +
            "TRUE ELSE FALSE END " +
            "FROM Users u WHERE u.email = ?1")
    Boolean selectExistUser(String email);

    Optional<Users> findByEmail(String email);
}
