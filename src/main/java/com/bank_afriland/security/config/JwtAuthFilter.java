package com.bank_afriland.security.config;

import com.bank_afriland.security.model.Token;
import com.bank_afriland.security.repository.TokenRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

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

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JtwService jwtService;
    private final UserDetailsService userDetailsService;
    private final TokenRepository tokenRepo;



    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain) throws ServletException, IOException {

//        if (request.getServletPath().contains("/api/v1/afriland/auth")) {
//            filterChain.doFilter(request, response);
//            return;
//        }
        //    header
        String authHeader = request.getHeader("Authorization");
        String token;
        String userEmail;

        //        Check if the head of request it's not null and started with Bearer
        if ((authHeader == null) || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        token = authHeader.substring(7);
        userEmail = jwtService.extractUserEmail(token);

        //        Check if user are already authenticated
        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//            Fetching user in database.
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);
//            Check if token is valid and valid new token in database.
                Boolean isValidToken  = tokenRepo.findByToken(token)
                    .map(t -> !t.isExpired() && !t.isRevoked())
                    .orElse(false);

            if (jwtService.isValidToken(token, userDetails) && isValidToken) {
//                Create user: UsernamePasswordAuthenticationToken
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
//                force security
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//                Update security context.
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
//            Give hand for the next request
            filterChain.doFilter(request, response);
        }
    }

}
