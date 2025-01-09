package com.example.swagger.auth.api;

import com.example.swagger.auth.application.SocialLoginService;
import com.example.swagger.auth.dto.SocialLoginRequest;
import com.example.swagger.auth.dto.SocialLoginResponse;
import com.example.swagger.global.CommonResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
@Tag(name = "SocialLogin", description = "소셜 로그인 API")
public class SocialLoginController {

    private final SocialLoginService socialLoginService;

    /**
     * 소셜 로그인 API
     *
     * @param request 소셜 로그인 요청 데이터
     * @return 소셜 로그인 응답 데이터
     */

    @PostMapping("/social-login")
    @Operation(summary = "소셜 로그인", description = "소셜 로그인 처리를 수행합니다.")
    public CommonResponse<SocialLoginResponse> socialLogin(@RequestBody SocialLoginRequest request) {
        SocialLoginResponse response = socialLoginService.socialLogin(request);
        return CommonResponse.success(response);
    }
}
