package com.BaeBrother.barrier_free_auth_application.barrier_free_auth_application.security.authority;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Long> {
    public List<Authority> findAllByAccountId(Long accountId);
}
