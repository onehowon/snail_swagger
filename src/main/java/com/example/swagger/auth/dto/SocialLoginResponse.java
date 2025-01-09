package com.example.swagger.auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
@Schema(description = "소셜 로그인 응답 DTO")
public class SocialLoginResponse {

    @Schema(description = "사용자 ID", example = "1")
    private final Long userId;

    @Schema(description = "JWT 토큰", example = "eyJhbGc0i...")
    private final String token;

    /**
     * SocialLoginResponse 생성자
     *
     * @param userId 사용자 ID
     * @param token JWT 토큰
     */
    public SocialLoginResponse(Long userId, String token) {
        this.userId = userId;
        this.token = token;
    }
}
