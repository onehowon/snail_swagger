package com.example.swagger.auth.api;

import com.example.swagger.auth.application.KakaoAuthService;
import com.example.swagger.auth.dto.AuthDTO;
import com.example.swagger.global.CommonResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
@Tag(name = "KakaoLogin", description = "카카오 로그인 API")
public class KakaoLoginController {

    private final KakaoAuthService kakaoAuthService;

    @PostMapping("/kakao-login")
    @Operation(summary = "카카오 로그인", description = "카카오 로그인 처리를 수행합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "로그인 성공",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CommonResponse.class),
                            examples = @ExampleObject(
                                    value = """
                {
                    "code": 200,
                    "message": "로그인 성공",
                    "data": {
                        "accessToken": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
                        "refreshToken": "refresh_12345678-abcd-1234-abcd-12345678abcd"
                    }
                }
                """
                            )
                    )
            ),
            @ApiResponse(responseCode = "400", description = "잘못된 요청 데이터",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CommonResponse.class),
                            examples = @ExampleObject(
                                    value = """
                {
                    "code": 400,
                    "message": "Authorization Code가 필요합니다.",
                    "data": null
                }
                """
                            )
                    )
            ),
            @ApiResponse(responseCode = "401", description = "인증 실패",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CommonResponse.class),
                            examples = @ExampleObject(
                                    value = """
                {
                    "code": 401,
                    "message": "인증 실패",
                    "data": null
                }
                """
                            )
                    )
            )
    })
    public ResponseEntity<?> kakaoLogin(@RequestBody AuthDTO.AuthRequest request) {
        if (request.getAuthorizationcode() == null || request.getAuthorizationcode().isEmpty()) {
            return ResponseEntity.badRequest().body(CommonResponse.success("Authorization Code가 필요합니다."));
        }

        AuthDTO.AuthResponse authResponse = kakaoAuthService.authenticate(request.getAuthorizationcode());
        if (authResponse == null) {
            return ResponseEntity.status(401).body(CommonResponse.success("인증 실패"));
        }

        return ResponseEntity.ok(CommonResponse.success(authResponse));
    }
}
