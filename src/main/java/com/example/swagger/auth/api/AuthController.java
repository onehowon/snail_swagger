package com.example.swagger.auth.api;

import com.example.swagger.auth.dto.AuthResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * AuthController
 * <p>
 * 인증과 관련된 API 엔드포인트를 제공합니다.
 * 소셜 로그인을 포함하여 인증 로직을 처리합니다.
 */
@RestController
@RequestMapping("/auth")
@Tag(name = "Auth", description = "인증 API")
public class AuthController {

    /**
     * 소셜 로그인 API
     * <p>
     * 사용자가 선택한 소셜 플랫폼과 액세스 토큰을 통해 인증을 수행합니다.
     *
     * @param provider    소셜 플랫폼 이름 (예: google, kakao, naver)
     * @param accessToken 소셜 플랫폼에서 제공한 엑세스 토큰
     * @return {@link AuthResponse} JWT 토큰, 사용자 ID, 신규 사용자 여부를 포함한 응답 객체
     */

    @PostMapping("/social-login")
    @Operation(summary = "소셜 로그인", description = "소셜 로그인 플랫폼을 이용하여 사용자 인증을 수행합니다.")
    @ApiResponse(responseCode = "200", description = "로그인 성공", content = @Content(schema = @Schema(implementation = AuthResponse.class)))
    @ApiResponse(responseCode = "400", description = "잘못된 요청")
    public AuthResponse socialLogin(
            @Parameter(description = "소셜 플랫폼 (google, kakao, naver)", example = "google") @RequestParam String provider,
            @Parameter(description = "소셜 엑세스 토큰", required = true) @RequestParam String accessToken) {
        // 예제 응답
        return new AuthResponse("jwt-token", "userId123", true);
    }
}
