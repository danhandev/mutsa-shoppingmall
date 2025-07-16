package com.mutsa.shoppingmall.service;

import com.mutsa.shoppingmall.domain.Cart;
import com.mutsa.shoppingmall.domain.CartItem;
import com.mutsa.shoppingmall.domain.Product;
import com.mutsa.shoppingmall.domain.User;
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
import com.mutsa.shoppingmall.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

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
    private final UserRepository userRepository;

    /**
     * 내 장바구니 조회 (fetch join, Mapper 분리)
     * 장바구니가 없으면 자동으로 생성하여 반환
     * @param email 로그인한 사용자 이메일
     * @return 장바구니 응답 DTO
     */
    @Transactional
    public CartResponse getMyCartByEmail(String email) {
        // 1. 먼저 장바구니 존재 여부 확인 (fetch join 없이)
        Optional<Cart> existingCart = cartRepository.findByUserEmail(email);
        
        Cart cart;
        if (existingCart.isPresent()) {
            // 2. 장바구니가 있으면 cart_items와 함께 조회
            cart = cartRepository.findByUserEmailFetchJoin(email)
                    .orElse(existingCart.get()); // fetch join에서 cart_items가 없으면 기본 cart 사용
        } else {
            // 3. 장바구니가 없으면 새로 생성
            cart = createEmptyCartForUser(email);
        }
        
        return cartMapper.toCartResponse(cart);
    }

    /**
     * 사용자를 위한 빈 장바구니 생성
     * @param email 사용자 이메일
     * @return 생성된 빈 장바구니
     */
    private Cart createEmptyCartForUser(String email) {
        // 사용자 조회
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));
        
        // 빈 장바구니 생성
        Cart newCart = Cart.builder()
                .user(user)
                .build();
        
        return cartRepository.save(newCart);
    }

    /**
     * 장바구니에 상품 추가
     * @param email 사용자 이메일
     * @param request 상품ID, 수량
     * @return 추가된 CartItem 정보
     */
    @Transactional
    public CartItemAddResponse addCartItem(String email, CartItemAddRequest request) {
        // 장바구니 조회 또는 생성 (조회 로직과 동일)
        Cart cart = cartRepository.findByUserEmail(email)
                .orElseGet(() -> createEmptyCartForUser(email));
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