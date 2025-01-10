package com.example.swagger.auth.api;

import com.example.swagger.auth.application.RefreshTokenService;
import com.example.swagger.auth.dto.RefreshTokenResponse;
import com.example.swagger.global.CommonResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
@Tag(name = "RefreshToken", description = "Refresh Token API")
public class RefreshTokenController {

    private final RefreshTokenService refreshTokenService;

    /**
     * Refresh Token 생성 API
     *
     * @return 생성된 Refresh Token
     */
    @GetMapping("/refresh-token")
    @Operation(summary = "Refresh Token 생성", description = "새로운 Refresh Token을 생성합니다.")
    @ApiResponse(responseCode = "200", description = "Refresh Token 생성 성공")
    public CommonResponse<RefreshTokenResponse> createRefreshToken() {
        RefreshTokenResponse response = refreshTokenService.createRefreshToken();
        return CommonResponse.success(response);
    }

}
