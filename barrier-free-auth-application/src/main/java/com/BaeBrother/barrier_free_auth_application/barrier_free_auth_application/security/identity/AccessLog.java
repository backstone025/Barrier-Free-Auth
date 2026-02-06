package com.BaeBrother.barrier_free_auth_application.barrier_free_auth_application.security.identity;

import com.BaeBrother.barrier_free_auth_application.barrier_free_auth_application.security.authority.Authority;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class AccessLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long accountId;
    private LocalDateTime settingDate;
    @ElementCollection
    private List<Authority> usedAthorities;
    private String description;
    private boolean isSuccess;
    private ActionType actionType;
}
