
package com.shoppingmall.product.repository;

import com.shoppingmall.product.entity.Product;
//db에서 데이터를 조회하려면 jpa 리포지토리가 필요함
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

//product entity를 기본키 타입 long으로 다룸.
public interface ProductRepository extends JpaRepository<Product, Long> {

    //상품의 이름으로 검색 가능하게 함
    List<Product> findByNameContaining(String name);

    List<Product> name(String name);
}
