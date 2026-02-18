package com.BaeBrother.barrier_free_auth_application.barrier_free_auth_application.security;

import com.BaeBrother.barrier_free_auth_application.barrier_free_auth_application.security.authority.UserAction;
import com.BaeBrother.barrier_free_auth_application.barrier_free_auth_application.security.authority.UserScope;

import java.util.List;

public enum ServiceMappingTable {

    // Account
    // Authority
    // Identity
    // Order
    ORDER_C_0(
            List.of("TEMP", "MAIN", "ADMIN"),
            UserAction.CREATE,
            UserScope.ORDER
    ),
    ORDER_R_0(
            List.of("TEMP", "MAIN", "ADMIN"),
            UserAction.READ,
            UserScope.ORDER
    ),
    ORDER_U_0(
            List.of("TEMP", "MAIN", "ADMIN"),
            UserAction.UPDATE,
            UserScope.ORDER
    ),
    ORDER_D_0(
            List.of("TEMP", "MAIN", "ADMIN"),
            UserAction.DELETE,
            UserScope.ORDER
    ),
    // Payment
    PAYMENT_C_0(
            List.of("TEMP", "MAIN", "ADMIN"),
            UserAction.CREATE,
            UserScope.PAYMENT
    ),
    PAYMENT_R_0(
            List.of("TEMP", "MAIN", "ADMIN"),
            UserAction.READ,
            UserScope.PAYMENT
    ),
    PAYMENT_U_0(
            List.of("ADMIN"),
            UserAction.UPDATE,
            UserScope.PAYMENT
    ),
    PAYMENT_D_0(
            List.of("ADMIN"),
            UserAction.DELETE,
            UserScope.PAYMENT
    ),
    // Product
    PRODUCT_C_0(
            List.of("TEMP", "MAIN", "ADMIN"),
            UserAction.CREATE,
            UserScope.PRODUCT
    ),
    PRODUCT_R_0(
            List.of("TEMP", "MAIN", "ADMIN"),
            UserAction.READ,
            UserScope.PRODUCT
    ),
    PRODUCT_U_0(
            List.of("TEMP", "MAIN", "ADMIN"),
            UserAction.UPDATE,
            UserScope.PRODUCT
    ),
    PRODUCT_D_0(
            List.of("TEMP", "MAIN", "ADMIN"),
            UserAction.UPDATE,
            UserScope.PRODUCT
    );
    private final List<String> accessableAccountType;
    private final UserAction action;
    private final UserScope scope;

    ServiceMappingTable(List<String> accessableAccountType, UserAction action, UserScope scope) {
        this.accessableAccountType = accessableAccountType;
        this.action = action;
        this.scope = scope;
    }

    public List<String> getAccessableAccountType() {
        return accessableAccountType;
    }

    public UserAction getAction() {
        return action;
    }

    public UserScope getScope() {
        return scope;
    }
}
