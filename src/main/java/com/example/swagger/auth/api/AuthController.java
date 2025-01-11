package com.example.swagger.auth.api;

import com.example.swagger.auth.dto.AuthDTO.AuthRequest;
import com.example.swagger.auth.dto.AuthDTO.AuthResponse;
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
@RestController
@RequestMapping("/auth")
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
                            schema = @Schema(implementation = AuthResponse.class))),
            @ApiResponse(responseCode = "400", description = "잘못된 요청 데이터",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping("/token")
    public ResponseEntity<?> issueToken(@RequestBody AuthRequest authRequest) {
        if (authRequest.getCode() == null || authRequest.getCode().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse("code 값이 유효하지 않습니다."));
        }

        String accessToken = generateToken();
        String refreshToken = generateToken();
        refreshTokenStore.put(refreshToken, LocalDateTime.now());

        AuthResponse authResponse = new AuthResponse(
                "Bearer",
                accessToken,
                refreshToken,
                TOKEN_VALIDITY_DAYS * 86400 // 30일 (초 단위)
        );

        return ResponseEntity.ok(authResponse);
    }

    /**
     * 토큰 갱신
     */
    @Operation(summary = "토큰 갱신", description = "만료된 토큰을 갱신합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "토큰 갱신 성공",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AuthResponse.class))),
            @ApiResponse(responseCode = "400", description = "잘못된 요청 데이터",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "401", description = "권한 없음",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping("/refresh")
    public ResponseEntity<?> refreshToken(@RequestHeader("Authorization") String refreshToken) {
        if (refreshToken == null || refreshToken.isEmpty() || !refreshToken.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse( "유효하지 않은 토큰입니다."));
        }

        refreshToken = refreshToken.replace("Bearer ", "");
        LocalDateTime issuedAt = refreshTokenStore.get(refreshToken);

        if (issuedAt == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ErrorResponse("토큰이 존재하지 않습니다."));
        }

        long daysSinceIssued = ChronoUnit.DAYS.between(issuedAt, LocalDateTime.now());
        if (daysSinceIssued >= TOKEN_VALIDITY_DAYS) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ErrorResponse("토큰이 만료되었습니다."));
        }

        if (daysSinceIssued >= TOKEN_HALF_LIFE_DAYS) {
            // 반감기가 지난 경우 토큰 갱신
            String newAccessToken = generateToken();
            String newRefreshToken = generateToken();
            refreshTokenStore.put(newRefreshToken, LocalDateTime.now());

            AuthResponse authResponse = new AuthResponse(
                    "Bearer",
                    newAccessToken,
                    newRefreshToken,
                    TOKEN_VALIDITY_DAYS * 86400 // 30일 (초 단위)
            );

            // 기존 토큰 삭제
            refreshTokenStore.remove(refreshToken);

            return ResponseEntity.ok(authResponse);
        }

        // 아직 유효한 경우
        AuthResponse authResponse = new AuthResponse(
                "Bearer",
                "accessTokenStillValidExample",
                refreshToken,
                (int) ((TOKEN_VALIDITY_DAYS - daysSinceIssued) * 86400) // 남은 유효기간
        );

        return ResponseEntity.ok(authResponse);
    }

    /**
     * 토큰 생성
     */
    private String generateToken() {
        return UUID.randomUUID().toString();
    }
}
