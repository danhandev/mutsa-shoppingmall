package com.mutsa.shoppingmall.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Swagger(OpenAPI) 기본 설정
 */
@Configuration
public class OpenApiConfig {

    /**
     * OpenAPI 문서 기본 정보 설정
     */
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Shopping Mall API")
                        .description("쇼핑몰 프로젝트 API 명세서")
                        .version("v1.0.0")
                );
    }
} 