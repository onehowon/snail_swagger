package com.example.swagger.auth.application;

import com.example.swagger.auth.dto.AuthResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * AuthService
 * <p>
 * 소셜 로그인 관련 비즈니스 로직을 처리하는 서비스 클래스입니다.
 */
@Service
@RequiredArgsConstructor
public class AuthService {
    /**
     * 소셜 로그인 처리
     *
     * @param provider    소셜 플랫폼 이름 (예: google, kakao, apple)
     * @param accessToken 소셜 플랫폼에서 제공한 엑세스 토큰
     * @return {@link AuthResponse} JWT 토큰, 사용자 ID, 신규 사용자 여부를 포함한 응답 객체
     */
    public AuthResponse socialLogin(String provider, String accessToken) {
        // 실제 로직 구현 예시
        // 1. Access Token 검증
        // 2. 사용자 정보 가져오기
        // 3. 신규 사용자 여부 확인

        // 임시 로직: 항상 동일한 응답 반환
        return new AuthResponse("jwt-token", "user-id", true);
    }
}
