package com.example.swagger.kakao.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Schema(description = "카카오 로그인 DTO")
public class KakaoLoginDTO {

    @Schema(description = "FCM 토큰", example = "fcm_token")
    private String fcmToken;

    @Schema(description = "카카오 액세스 토큰", example = "kakao_access")
    private String kakaoAccessToken;
}
