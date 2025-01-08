package com.example.swagger.auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * AuthResponse
 * <p>
 * 소셜 로그인 응답 DTO 클래스입니다.
 * 인증 성공 시 반환되는 정보를 포함합니다.
 */
@Getter
@AllArgsConstructor
@Schema(description = "소셜 로그인 응답 DTO")
public class AuthResponse {

    /**
     * JWT 토큰
     * <p>
     * 인증 성공 시 발급되는 토큰입니다.
     */

    @Schema(description = "JWT 토큰", example = "eyJhbGciOiJIUzI1...")
    private final String token;

    /**
     * 사용자 ID
     * <p>
     * 소셜 로그인 성공 후 생성되거나 조회된 고유 사용자 ID입니다.
     */

    @Schema(description = "사용자 ID", example = "userId123")
    private final String userId;

    /**
     * 신규 사용자 여부
     * <p>
     * true이면 신규 사용자, false이면 기존 사용자입니다.
     */

    @Schema(description = "신규 사용자 여부", example = "true")
    private final boolean isNewUser;
}