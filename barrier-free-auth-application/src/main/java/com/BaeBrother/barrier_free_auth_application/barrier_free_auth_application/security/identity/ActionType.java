package com.BaeBrother.barrier_free_auth_application.barrier_free_auth_application.security.identity;

public enum ActionType {
    // 해당 권한을 사용함
    USE,
    // 해당 권한이 거부됨
    DENIED,
    // 해당 권한이 회수됨
    REVOKE
}
