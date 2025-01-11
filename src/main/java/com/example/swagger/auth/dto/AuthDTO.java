package com.example.swagger.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

public class AuthDTO {
    @Data
    @AllArgsConstructor
    public static class AuthRequest {
        private String code;
    }

    @Data
    @AllArgsConstructor
    public static class AuthResponse {
        private String tokenType;
        private String accessToken;
        private String refreshToken;
        private int expiresIn;
    }
}