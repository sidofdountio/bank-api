package com.bank_afriland.security.model;

import com.bank_afriland.project.model.entity.AuditMetadata;
import com.bank_afriland.security.UserRole;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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

@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(
        name = "users",
        uniqueConstraints = @UniqueConstraint(columnNames = "email", name = "UQ_user_email"))
public class Users extends AuditMetadata implements UserDetails, Principal {
    @Id
    @SequenceGenerator(name = "users_id_sequence", allocationSize = 0, initialValue = 0, sequenceName = "users_id_sequence")
    @GeneratedValue(generator = "users_id_sequence", strategy = GenerationType.SEQUENCE)
    @Column(name = "user_id")
    private Long userId;

    @NotEmpty(message = "Last name cannot be empty")
    private String lastName;

    private String firstName;

    @NotEmpty(message = "Email name cannot be empty")
    @Email(message = "Invalid email. Please enter valid email.")
    @Column(nullable = false, name = "email")
    private String email;
    @NotEmpty(message = "Password cannot be empty")
    @Size(min = 8, message = "Password should be 8 character long minimum")
    private String password;
    private String phone;

    private String address;
    private String imageUrl;
    private boolean enable;
    private boolean accountLocked;
    private boolean isUsingMfa;
    @Enumerated(EnumType.STRING)
    private UserRole role;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Token> tokens = new ArrayList<>();
    public Users(String lastName, String email, String password, String phone, String address, String imageUrl) {
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.address = address;
        this.imageUrl = imageUrl;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role.getAuthorities();
    }


    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }


    public String fullName() {
        return firstName + " " + lastName;
    }


    public Long getUserId() {
        return userId;
    }


    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public boolean isAccountLocked() {
        return accountLocked;
    }

    public void setAccountLocked(boolean accountLocked) {
        this.accountLocked = accountLocked;
    }

    public boolean isUsingMfa() {
        return isUsingMfa;
    }

    public void setUsingMfa(boolean usingMfa) {
        isUsingMfa = usingMfa;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    @Override
    public String getName() {
        return "";
    }
}
