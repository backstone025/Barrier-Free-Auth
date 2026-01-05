package com.BaeBrother.barrier_free_auth_application.barrier_free_auth_application.security.account;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("MAIN")
public class MainAccount extends Account {
}
