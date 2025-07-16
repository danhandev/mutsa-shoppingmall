package com.mutsa.shoppingmall.service;

import com.mutsa.shoppingmall.domain.Cart;
import com.mutsa.shoppingmall.domain.CartItem;
import com.mutsa.shoppingmall.domain.Product;
import com.mutsa.shoppingmall.dto.cart.CartResponse;
import com.mutsa.shoppingmall.dto.cart.CartItemAddRequest;
import com.mutsa.shoppingmall.dto.cart.CartItemAddResponse;
import com.mutsa.shoppingmall.exception.CartNotFoundException;
import com.mutsa.shoppingmall.exception.ErrorCode;
import com.mutsa.shoppingmall.exception.BusinessException;
import com.mutsa.shoppingmall.exception.CartItemNotFoundException;
import com.mutsa.shoppingmall.repository.CartRepository;
import com.mutsa.shoppingmall.repository.CartItemRepository;
import com.mutsa.shoppingmall.repository.ProductRepository;
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
    private final ProductRepository productRepository;
    private final CartItemRepository cartItemRepository;

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

    /**
     * 장바구니에 상품 추가
     * @param email 사용자 이메일
     * @param request 상품ID, 수량
     * @return 추가된 CartItem 정보
     */
    @Transactional
    public CartItemAddResponse addCartItem(String email, CartItemAddRequest request) {
        Cart cart = cartRepository.findByUserEmailFetchJoin(email)
                .orElseThrow(() -> new BusinessException(ErrorCode.CART_NOT_FOUND));
        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new BusinessException(ErrorCode.PRODUCT_NOT_FOUND));
        // 이미 담긴 상품이어도 새로운 CartItem 생성
        CartItem cartItem = CartItem.builder()
                .cart(cart)
                .product(product)
                .quantity(request.getQuantity())
                .build();
        cartItemRepository.save(cartItem);
        return cartMapper.toCartItemAddResponse(cartItem);
    }

    /**
     * 장바구니 상품 삭제 (사용자 권한 검증 포함)
     * @param cartItemId 삭제할 장바구니 항목 ID
     * @param email 로그인한 사용자 이메일 (권한 검증용)
     */
    @Transactional
    public void deleteCartItem(Long cartItemId, String email) {
        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(CartItemNotFoundException::new);
        
        // 권한 검증: 해당 cartItem이 요청한 사용자의 것인지 확인
        String cartOwnerEmail = cartItem.getCart().getUser().getEmail();
        if (!cartOwnerEmail.equals(email)) {
            throw new BusinessException(ErrorCode.UNAUTHORIZED_ACCESS);
        }
        
        cartItemRepository.delete(cartItem);
    }
} 