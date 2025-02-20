package com.bank_afriland.project.service;

import com.bank_afriland.security.model.Users;
import com.bank_afriland.security.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

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


@Service
@Slf4j
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<Users> users() {
//        log.info("Fetching users");
        return userRepository.findAll();
    }

    public Users user(Long userId) {
//        log.info("Fetching user by userId");
        return userRepository.findById(userId).orElseThrow(()-> new UsernameNotFoundException("User not found"));
    }

    public Users myProfile(Authentication authentication) {
        return findUserByUsername(authentication.getName());
    }

    public Users findUserByUsername(String email) {
//        log.info("Find user by email {} ", email);
        return userRepository.findByEmail(email).orElseThrow(() -> new EntityNotFoundException("No account match with this email " + email));
    }
}
