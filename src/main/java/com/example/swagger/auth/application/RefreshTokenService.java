package com.example.swagger.auth.application;

import com.example.swagger.auth.dto.RefreshTokenResponse;
import org.springframework.stereotype.Service;

@Service
public class RefreshTokenService {
    /**
     * Refresh Token 생성
     *
     * @return 생성된 Refresh Token 응답 데이터
     */
    public RefreshTokenResponse createRefreshToken() {
        // 임시 로직 : 실제로는 JWT 생성 로직
        String token = "eyJhbcGioiu..";
        String expiresAt = "2025-01-01T00:00:00";

        return new RefreshTokenResponse(token, expiresAt);
    }
}
