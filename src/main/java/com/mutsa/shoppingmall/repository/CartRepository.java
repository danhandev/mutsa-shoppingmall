package com.mutsa.shoppingmall.repository;

import com.mutsa.shoppingmall.domain.Cart;
import com.mutsa.shoppingmall.domain.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Optional;

/**
 * 장바구니(Cart) JPA Repository
 */
public interface CartRepository extends JpaRepository<Cart, Long> {
    /**
     * 사용자로 장바구니 조회 (cartItems, product fetch join)
     */
    @EntityGraph(attributePaths = {"cartItems", "cartItems.product"})
    Optional<Cart> findByUser(User user);

    /**
     * 사용자 이메일로 장바구니 fetch join 조회 (JPQL)
     */
    @Query("SELECT c FROM Cart c JOIN FETCH c.cartItems ci JOIN FETCH ci.product p WHERE c.user.email = :email")
    Optional<Cart> findByUserEmailFetchJoin(@Param("email") String email);

    /**
     * 사용자 이메일로 장바구니 조회 (cart_items 없어도 반환)
     */
    @Query("SELECT c FROM Cart c WHERE c.user.email = :email")
    Optional<Cart> findByUserEmail(@Param("email") String email);
} 