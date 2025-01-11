package com.example.swagger.kakao.application;

import com.example.swagger.kakao.dto.KakaoUserInfoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class KakaoService {


    // 생략된 의존성 및 변수

    /**
     * 카카오 액세스 토큰을 사용해 사용자 정보를 가져옵니다.
     *
     * @param kakaoAccessToken 카카오 액세스 토큰
     * @return {@link KakaoUserInfoResponse}
     */

    public KakaoUserInfoResponse getUserInfo(String kakaoAccessToken) {
        return new KakaoUserInfoResponse("example@kakao.com");
    }

    /**
     *
     * @param email 카카오 이메일
     * @param token FCM 토큰
     * @return {@link SignInDTO} 로그인 결과
     */

    @Transactional
    public SignInDTO loginWithKakao(String email, String token) {
        // TODO: 실제 로그인 로직 구현 필요
        return new SignInDTO(1L, "generated_access_token", "generated_refresh_token", "user_nickname", true);
    }

    /**
     * 새로운 사용자를 카카오 계정 기반으로 가입 처리
     *
     * @param email 카카오 이메일
     * @return {@link User} 생성된 회원 객체
     */
}
