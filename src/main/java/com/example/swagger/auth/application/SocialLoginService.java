package com.example.swagger.auth.application;

import com.example.swagger.auth.dto.SocialLoginRequest;
import com.example.swagger.auth.dto.SocialLoginResponse;
import org.springframework.stereotype.Service;

@Service
public class SocialLoginService {

    /**
     * 소셜 로그인 처리
     *
     * @param request 소셜 로그인 요청 데이터
     * @return 소셜 로그인 응답 데이터
     */
    public SocialLoginResponse socialLogin(SocialLoginRequest request){
        Long userId = 1L;
        String token = "jwt-token-placeholder";

        return new SocialLoginResponse(userId, token);
    }
}
