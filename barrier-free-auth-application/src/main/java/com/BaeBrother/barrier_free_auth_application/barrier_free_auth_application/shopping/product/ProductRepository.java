package com.BaeBrother.barrier_free_auth_application.barrier_free_auth_application.shopping.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
