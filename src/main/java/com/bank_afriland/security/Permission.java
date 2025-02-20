package com.bank_afriland.security;

import lombok.Getter;

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
public enum Permission {
    ADMIN_READ("admin:read"),
    ADMIN_CREATE("admin:create"),
    ADMIN_UPDATE("admin:update"),
    ADMIN_DELETE("admin:delete"),
    MANAGER_READ("manager:read"),
    MANAGER_UPDATE("manager:update"),
    MANAGER_CREATE("manager:create"),
    SYSADMIN_READ("sysadmin:read"),
    SYSADMIN_UPDATE("sysadmin:update"),
    SYSADMIN_DELETE("sysadmin:delete"),
    SYSADMIN_CREATE("sysadmin:create"),
    USER_READ("user:read"),
    USER_CREATE("user:create"),
    USER_UPDATE("user:update");


    private final String permision;

    Permission(String permision) {
        this.permision = permision;
    }

    public String getPermision() {
        return permision;
    }
}
