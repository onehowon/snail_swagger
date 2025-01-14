package com.example.swagger.auth.api;

import com.example.swagger.auth.dto.AuthDTO.AuthRequest;
import com.example.swagger.auth.dto.AuthDTO.AuthResponse;
import com.example.swagger.global.CommonResponse;
import com.example.swagger.global.ErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Auth API
 * 인증 및 토큰 관리
 */
/**
 * Auth API
 * 인증 및 토큰 관리
 */
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "Auth", description = "인증 관련 API")
public class AuthController {

    private final Map<String, LocalDateTime> refreshTokenStore = new HashMap<>();

    private static final int TOKEN_VALIDITY_DAYS = 30; // 토큰 유효기간 30일
    private static final int TOKEN_HALF_LIFE_DAYS = 15; // 반감기 15일

    /**
     * 토큰 발급
     */
    @Operation(summary = "토큰 발급", description = "로그인 후 인증 토큰을 발급받습니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "토큰 발급 성공",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CommonResponse.class,
                                    example = """
                                            {
                                                "code": 200,
                                                "message": "토큰이 성공적으로 발급되었습니다.",
                                                "data": {
                                                    "refreshToken": "sampleRefreshToken123",
                                                    "accessToken": "sampleAccessToken123"
                                                }
                                            }
                                            """))),
            @ApiResponse(responseCode = "400", description = "잘못된 요청 데이터",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CommonResponse.class,
                                    example = """
                                            {
                                                "code": 400,
                                                "message": "유효하지 않은 요청입니다.",
                                                "data": null
                                            }
                                            """))),
            @ApiResponse(responseCode = "401", description = "권한 없음",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CommonResponse.class,
                                    example = """
                                            {
                                                "code": 401,
                                                "message": "인증되지 않은 사용자입니다.",
                                                "data": null
                                            }
                                            """)))
    })
    @PostMapping("/token")
    public ResponseEntity<?> issueToken(@RequestBody AuthRequest authRequest) {
        if (authRequest.getAuthorizationcode() == null || authRequest.getAuthorizationcode().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(CommonResponse.success("code 값이 유효하지 않습니다."));
        }

        String accessToken = generateToken();
        String refreshToken = generateToken();
        refreshTokenStore.put(refreshToken, LocalDateTime.now());

        AuthResponse authResponse = new AuthResponse(
                accessToken,
                refreshToken
        );

        return ResponseEntity.ok(CommonResponse.success(authResponse));
    }

    /**
     * 토큰 갱신
     */
    @Operation(summary = "토큰 갱신", description = "만료된 토큰을 갱신합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "토큰 갱신 성공",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CommonResponse.class))),
            @ApiResponse(responseCode = "400", description = "잘못된 요청 데이터",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CommonResponse.class))),
            @ApiResponse(responseCode = "401", description = "권한 없음",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CommonResponse.class)))
    })
    @PostMapping("/refresh")
    public ResponseEntity<?> refreshToken(@RequestHeader("Authorization") String refreshToken) {
        if (refreshToken == null || refreshToken.isEmpty() || !refreshToken.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(CommonResponse.success("유효하지 않은 토큰입니다."));
        }

        refreshToken = refreshToken.replace("Bearer ", "");
        LocalDateTime issuedAt = refreshTokenStore.get(refreshToken);

        if (issuedAt == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(CommonResponse.success("토큰이 존재하지 않습니다."));
        }

        long daysSinceIssued = ChronoUnit.DAYS.between(issuedAt, LocalDateTime.now());
        if (daysSinceIssued >= TOKEN_VALIDITY_DAYS) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(CommonResponse.success("토큰이 만료되었습니다."));
        }

        if (daysSinceIssued >= TOKEN_HALF_LIFE_DAYS) {
            // 반감기가 지난 경우 토큰 갱신
            String newAccessToken = generateToken();
            String newRefreshToken = generateToken();
            refreshTokenStore.put(newRefreshToken, LocalDateTime.now());

            AuthResponse authResponse = new AuthResponse(
                    newAccessToken,
                    newRefreshToken
            );

            // 기존 토큰 삭제
            refreshTokenStore.remove(refreshToken);

            return ResponseEntity.ok(CommonResponse.success(authResponse));
        }

        // 아직 유효한 경우
        AuthResponse authResponse = new AuthResponse(
                "accessTokenStillValidExample",
                refreshToken
        );

        return ResponseEntity.ok(CommonResponse.success(authResponse));
    }

    /**
     * 토큰 생성
     */
    private String generateToken() {
        return UUID.randomUUID().toString();
    }
}
