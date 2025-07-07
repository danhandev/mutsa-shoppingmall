package com.mutsa.shoppingmall.repository;

import com.mutsa.shoppingmall.domain.Cart;
import com.mutsa.shoppingmall.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findByUser(User user);
} 