package com.mutsa.shoppingmall.service;

import com.mutsa.shoppingmall.repository.ProductRepository;
import com.mutsa.shoppingmall.domain.Product;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    //상품 전체조회
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    //상품 개별조회
    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    //상품이름으로 검색
    public List<Product> getProductByName(String name) {
        return productRepository.findByNameContaining(name);
    }
}
