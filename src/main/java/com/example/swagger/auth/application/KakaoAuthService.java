package com.example.swagger.auth.application;

import com.example.swagger.auth.dto.AuthDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class KakaoAuthService {

    private static final String TOKEN_URL = "https://kauth.kakao.com/oauth/token";
    private static final String USER_INFO_URL = "https://kapi.kakao.com/v2/user/me";

    public AuthDTO.AuthResponse authenticate(String authorizationCode) {
        // 1. Authorization Code로 Access Token 요청
        String accessToken = requestAccessToken(authorizationCode);

        if (accessToken == null) {
            return null;
        }

        // 2. Refresh Token 생성
        String refreshToken = generateRefreshToken();

        // 3. 인증 응답 반환
        return new AuthDTO.AuthResponse(accessToken, refreshToken);
    }

    private String requestAccessToken(String authorizationCode) {
        RestTemplate restTemplate = new RestTemplate();

        // 카카오 토큰 요청 파라미터
        String requestBody = String.format(
                "grant_type=authorization_code&client_id=%s&redirect_uri=%s&code=%s",
                "YOUR_CLIENT_ID",
                "YOUR_REDIRECT_URI",
                authorizationCode
        );

        // POST 요청
        ResponseEntity<String> response = restTemplate.postForEntity(TOKEN_URL, requestBody, String.class);

        // Access Token 추출 로직 구현
        return parseAccessToken(response.getBody());
    }

    private String generateRefreshToken() {
        // Refresh Token 생성 로직 (UUID 사용 예제)
        return "refresh_" + java.util.UUID.randomUUID().toString();
    }

    private String parseAccessToken(String response) {
        // JSON 파싱 로직 구현
        return "parsedAccessToken";
    }
}