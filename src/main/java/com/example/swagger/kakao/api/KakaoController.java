package com.example.swagger.kakao.api;

import com.example.swagger.kakao.application.KakaoService;
import com.example.swagger.kakao.application.SignInDTO;
import com.example.swagger.kakao.dto.KakaoLoginDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kakao")
@RequiredArgsConstructor
@Tag(name = "Kakao", description = "카카오 로그인 API")
public class KakaoController {

    private final KakaoService kakaoService;

    /** 카카오 로그인 처리
     *
     * @param kakaoLoginDTO 카카오 로그인 DTO
     * @return 로그인 결과 DTO
     */

    @PostMapping("/login")
    @Operation(summary = "카카오 로그인", description = "카카오 계정을 이용한 로그인을 수행합니다.")
    @ApiResponse(responseCode = "200", description = "로그인 성공")
    @ApiResponse(responseCode = "400", description = "잘못된 요청")
    public ResponseEntity<SignInDTO> loginwithKakao(@RequestBody KakaoLoginDTO kakaoLoginDTO){
        SignInDTO signInDTO = kakaoService.loginWithKakao(kakaoLoginDTO.getKakaoAccessToken(), kakaoLoginDTO.getFcmToken());
        return ResponseEntity.ok(signInDTO);
    }
}
