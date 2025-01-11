package com.example.swagger.kakao.application;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@Schema(description = "로그인 응답 DTO")
public class SignInDTO {

    @Schema(description = "회원 ID", example = "123")
    private Long id;

    @Schema(description = "Access Token", example = "access_token")
    private String accessToken;

    @Schema(description = "Refresh Token", example = "refresh_token")
    private String refreshToken;

    @Schema(description = "회원 닉네임", example = "user123")
    private String nickname;

    @Schema(description = "온보딩 여부", example = "true")
    private boolean onboarded;
}
