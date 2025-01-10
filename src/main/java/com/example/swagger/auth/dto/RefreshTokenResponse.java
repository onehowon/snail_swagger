package com.example.swagger.auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
@Schema(description = "Refresh Token 응답 DTO")
public class RefreshTokenResponse {

    @Schema(description = "Refresh Token", example = "eyJhbGciOiJi,...")
    private final String refreshToken;

    @Schema(description = "토큰 만료 시간", example = "2025-01-01T00:00:00")
    private final String expiresAt;

    public RefreshTokenResponse(String refreshToken, String expiresAt) {
        this.refreshToken = refreshToken;
        this.expiresAt = expiresAt;
    }
}
