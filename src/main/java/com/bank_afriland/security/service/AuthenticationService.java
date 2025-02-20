package com.bank_afriland.security.service;

import com.bank_afriland.security.UserRole;
import com.bank_afriland.security.config.JtwService;
import com.bank_afriland.security.dto.UsersMapper;
import com.bank_afriland.security.model.Token;
import com.bank_afriland.security.model.Users;
import com.bank_afriland.security.repository.TokenRepository;
import com.bank_afriland.security.repository.UserRepository;
import com.bank_afriland.security.request.AuthRequest;
import com.bank_afriland.security.request.AuthResponse;
import com.bank_afriland.security.request.RegisterRequest;
import com.bank_afriland.security.request.RegisterResponse;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Map;

import static java.time.LocalDateTime.now;

/**
 * <blockquote><pre>
 * Author   : @Dountio
 * LinkedIn : @SidofDountio
 * GitHub   : @SidofDountio
 * Version  : V1.0
 * Email    : sidofdountio406@gmail.com
 * Since    : 2/16/25
 * </blockquote></pre>
 */

@Service
@Slf4j
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenRepository tokenRepository;
    private final JtwService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UsersMapper usersMapper;
    @Value("${application.image}")
    String DEFAULT_USER_IMAGE_PROFILE;


    public AuthenticationService(UserRepository userRepository,
                                 PasswordEncoder passwordEncoder,
                                 TokenRepository tokenRepository,
                                 JtwService jwtService,
                                 AuthenticationManager authenticationManager,
                                 UsersMapper usersMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenRepository = tokenRepository;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.usersMapper = usersMapper;
    }

    public RegisterResponse create(RegisterRequest request) throws BadRequestException {
//            Check the email is unique.
        Boolean existUser = userRepository.selectExistUser(request.email().trim().toLowerCase());
        if (existUser) {
            log.error("Email already in use. Please different email");
            throw new BadRequestException("Email already in use. Please different email");
        }
        Users newUsers = usersMapper.toUsers(request);
        newUsers.setRole(UserRole.ADMIN);
        log.info("Saving New User Users {}", newUsers);
        Users usersSaved = userRepository.save(newUsers);
//          Generate token.
        String activationCode = generateActivationCode(usersSaved);
//        Send email to Users with verification email.
        validationAccount(newUsers.getEmail());
        return RegisterResponse.builder().username(request.lastName()).build();
    }


    public AuthResponse authenticate(AuthRequest request) throws BadRequestException {
        Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
//        claim
        var user = ((Users) auth.getPrincipal());
        var users = userRepository.findByEmail(request.getEmail()).orElseThrow(() -> new BadCredentialsException("Users with account " + request.getEmail() + " not exist."));
//
        var tokenGenerated = jwtService.generateToken(users);
        log.info("User connected");
        //        revoke the previous token
        revokeToken(users);
//        Generate and save new token.
        saveNewToken(users, tokenGenerated);
        return AuthResponse.builder()
                .token(tokenGenerated)
                .username(users.getEmail())
                .build();
    }

    @Transactional
    private void validationAccount(String email) {
        Users userByUsername = findUserByUsername(email);
        userByUsername.setEnable(true);
        revokeToken(userByUsername);
    }

    @Transactional
    public String activateAccount(String token) {
        var savedToken = tokenRepository.findByToken(token).orElseThrow(() -> new RuntimeException("Invalid token"));

        var userById = userRepository.findById(savedToken.getUser().getUserId()).orElseThrow(() -> new UsernameNotFoundException("Users not found"));
//        log.info("Fetching users {} by ID {}", userById, userById.getUserId());
        userById.setEnable(true);
//        Revoke token.
        revokeToken(userById);
        return "Account activated";
    }

    /**
     * Generate secure random number to activate or validated users account.
     * The secure random number is called code. When is generated we save it into token table before send to  users by email.
     */
    public String generateActivationCode(Users users) {
        String generatedToken = generatedToken(6);
        var token = Token.builder()
                .token(generatedToken)
                .refreshToken(null)
                .user(users)
                .createAt(LocalDateTime.now())
                .expireAt(LocalDateTime.now().plusMinutes(15))
                .revoked(false)
                .expired(false)
                .build();
//        log.info("saving token {}", token);
        tokenRepository.save(token);
        return generatedToken;
    }

    /**
     * Save users and set new token to her.
     */
    private void saveNewToken(Users users, String token) {
        var tokeToSave = Token
                .builder()
                .user(users)
                .expired(false)
                .revoked(false)
                .token(token)
                .createAt(now())
                .build();
//        log.info(" save users token after login,logout or update: {}", token);
        tokenRepository.save(tokeToSave);
    }

    /**
     * revoke all users token
     */
    public void revokeToken(Users users) {
        var validUserToken = tokenRepository.findAllValidTokensByUser(users.getUserId());
        if (validUserToken.isEmpty())
            return;
        validUserToken.forEach(token -> {
            token.setRevoked(true);
            token.setExpired(true);
        });
        log.info("users token had been revoked while login or logout : ");
        tokenRepository.saveAll(validUserToken);
    }


    /**
     * Generate here
     */
    private String generatedToken(int length) {
        String characters = "0123456789";
        StringBuilder activationCode = new StringBuilder();
        SecureRandom random = new SecureRandom();
        for (int i = 0; i < length; i++) {
            activationCode.append(characters.charAt(random.nextInt(characters.length())));
        }
        return activationCode.toString();
    }


    public Users user(Long userId) {
//        log.info("Fetching user by userId");
        return userRepository.findById(userId).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    public Users findUserByUsername(String email) {
//        log.info("Find user by email {} ", email);
        return userRepository.findByEmail(email).orElseThrow(() -> new EntityNotFoundException("No account match with this email " + email));
    }


}
