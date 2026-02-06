package com.BaeBrother.barrier_free_auth_application.barrier_free_auth_application.security.authority;


import java.util.List;

public enum Role {
    MAIN_USER(List.of(
            Authority.ACCESS_PAYMENT, Authority.READ_PAYMENT,
            Authority.READ_MAPPING_BETWEEN_ACCOUNTS,
            Authority.AUTHORIZATION_OF_ORDER, Authority.AUTHORIZATION_OF_PRODUCT
    )),
    TEMP_USER  (List.of(
            Authority.CREATE_ORDER, Authority.READ_ORDER, Authority.UPDATE_ORDER, Authority.DELETE_ORDER,
            Authority.READ_PRODUCT,
            Authority.ACCESS_PAYMENT, Authority.READ_PAYMENT
    ));

    public List<Authority> getAthorities() {
        return athorities;
    }

    private final List<Authority> athorities;

    Role(List<Authority> athorities) {
        this.athorities = athorities;
    }
}
