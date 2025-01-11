package com.example.swagger.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class Swagger {

    /**
     * Nailian API 문서 설정
     *
     * @return OpenAPI 객체
     */
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Nailian API Documentation")
                        .version("1.0.0")
                        .description("Nailian App API 문서"));
    }
}
