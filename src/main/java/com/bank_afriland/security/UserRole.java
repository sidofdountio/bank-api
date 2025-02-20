package com.bank_afriland.security;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.bank_afriland.security.Permission.*;


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

@Getter
public enum UserRole {
    USER(Set.of(USER_READ,USER_CREATE,USER_UPDATE)),
    ADMIN(Set.of(Permission.ADMIN_READ, Permission.ADMIN_UPDATE, ADMIN_CREATE, Permission.ADMIN_DELETE)),
    SYSADMIN(Set.of(Permission.SYSADMIN_CREATE, Permission.SYSADMIN_UPDATE, Permission.SYSADMIN_DELETE, Permission.SYSADMIN_READ)),
    MANAGER(Set.of(Permission.MANAGER_READ,Permission.MANAGER_UPDATE));
    private final Set<Permission> permissions;


    UserRole(Set<Permission> permissions) {
        this.permissions = permissions;
    }


    public Set<Permission> getPermissions() {
        return permissions;
    }

    public List<SimpleGrantedAuthority> getAuthorities() {
        var authorities = getPermissions()
                .stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermision()))
                .collect(Collectors.toList());
        authorities.add(new SimpleGrantedAuthority("ROLE_" +this.name()));
        return authorities;
    }




}
