package com.mutsa.shoppingmall.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
// import com.mutsa.shoppingmall.domain.cart.Cart;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

/**
 * 회원(User) 엔티티
 * - 로그인 사용자 정보
 * - 장바구니와 1:1 연관
 */
@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** 이메일 (로그인 ID 역할) */
    @Column(nullable = false, unique = true, length = 100)
    private String email;

    /** 사용자 이름 */
    @Column(nullable = false, length = 50)
    private String name;

    /** 비밀번호 (암호화 저장 필수) */
    @JsonIgnore
    @Column(nullable = false, length = 100)
    private String password;

    /** 사용자 장바구니 (1:1 관계, 역방향) */
    // @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    // private Cart cart;

    /** 생성 일시 */
    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    /** 수정 일시 */
    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    /** 비밀번호 변경 메서드 */
    public void changePassword(String encodedPassword) {
        this.password = encodedPassword;
    }

    // /** 장바구니 할당 메서드 */
    // public void assignCart(Cart cart) {
    //     this.cart = cart;
    //     cart.setUser(this);
    // }
}
