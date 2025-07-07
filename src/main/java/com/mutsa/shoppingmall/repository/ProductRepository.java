package com.mutsa.shoppingmall.repository;

import com.mutsa.shoppingmall.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
} 