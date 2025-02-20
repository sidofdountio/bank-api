package com.bank_afriland.config;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

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


public class ApplicationAuditAware implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            // Check if the principal is an instance of UserDetails (or any custom user class)
            Object principal = authentication.getPrincipal();
            if (principal instanceof UserDetails) { // Replace with your UserDetails implementation
                return Optional.of(((UserDetails) principal).getUsername());
            } else if (principal instanceof String) {
                return Optional.of((String) principal); // In case username is returned as a String
            }
        }

        return Optional.empty(); // If no authenticated user is found
    }

}