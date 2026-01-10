package com.BaeBrother.barrier_free_auth_application.barrier_free_auth_application.security.account;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "temp_accounts")
@DiscriminatorValue("TEMP")
public class TempAccount extends Account {
}
