package com.example.swagger.auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

/**
 * 소셜 로그인 요청 DTO 클래스
 */
@Getter
@Schema(description = "소셜 로그인 요청 DTO")
public class SocialLoginRequest {

    @Schema(description = "소셜 플랫폼", example = "google")
    private final String platform;

    @Schema(description = "소셜 플랫폼 사용자 ID", example = "platform_user_1")
    private final String platformUserId;

    /**
     * SocialLoginRequest 생성자
     *
     * @param platform 소셜 플랫폼
     * @param platformUserId 소셜 플랫폼 사용자 ID
     */
    public SocialLoginRequest(String platform, String platformUserId){
        this.platform = platform;
        this.platformUserId = platformUserId;
    }
}
