package com.example.swagger.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * AuthDTO
 * 인증 요청 및 응답 데이터
 */
public class AuthDTO {

    /**
     * 인증 요청 데이터
     */
    @Data
    @AllArgsConstructor
    public static class AuthRequest {
        private String Authorizationcode; // 인증 코드
    }

    /**
     * 인증 응답 데이터
     */
    @Data
    @AllArgsConstructor
    public static class AuthResponse {
        private String accessToken;  // Access Token
        private String refreshToken; // Refresh Token
    }
}