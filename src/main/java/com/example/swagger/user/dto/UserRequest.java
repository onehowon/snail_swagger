package com.example.swagger.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

/**
 * 사용자 등록 요청 DTO 클래스
 */
@Getter
@Schema(description = "사용자 등록 요청 DTO")
public class UserRequest {

    @Schema(description = "닉네임", example = "user1")
    private final String nickname;

    public UserRequest(String nickname) {
        this.nickname = nickname;
    }
}
