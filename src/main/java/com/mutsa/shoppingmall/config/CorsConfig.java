package com.mutsa.shoppingmall.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class CorsConfig {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // 모든 경로에 대해
                        .allowedOrigins("http://localhost:3000","http://localhost:5173")//허용할 프론트 도메인
                        .allowedMethods("GET","POST","PUT","DELETE","OPTIONS","PATCH")//허용할 http 메서드
                        .allowedHeaders("*")//모든 헤더 허용
                        .exposedHeaders("*")//모든 헤더 노출
                        .allowCredentials(true)
                        .maxAge(3600);
            }
        };
    }
}