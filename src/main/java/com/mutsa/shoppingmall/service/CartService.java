package com.mutsa.shoppingmall.service;

import com.mutsa.shoppingmall.domain.Cart;
import com.mutsa.shoppingmall.dto.cart.CartResponse;
import com.mutsa.shoppingmall.exception.CartNotFoundException;
import com.mutsa.shoppingmall.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 장바구니 관련 비즈니스 로직 서비스
 */
@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final CartMapper cartMapper;

    /**
     * 내 장바구니 조회 (fetch join, Mapper 분리)
     * @param email 로그인한 사용자 이메일
     * @return 장바구니 응답 DTO
     */
    @Transactional(readOnly = true)
    public CartResponse getMyCartByEmail(String email) {
        Cart cart = cartRepository.findByUserEmailFetchJoin(email)
                .orElseThrow(CartNotFoundException::new);
        return cartMapper.toCartResponse(cart);
    }
} 