package com.mutsa.shoppingmall.repository;

import com.mutsa.shoppingmall.domain.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
} 