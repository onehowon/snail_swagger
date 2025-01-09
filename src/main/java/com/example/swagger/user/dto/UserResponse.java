package com.example.swagger.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

/**
 * 사용자 정보 응답 DTO 클래스
 */
@Getter
@Schema(description = "사용자 정보 응답 DTO")
public class UserResponse {

    @Schema(description = "사용자 ID", example = "1")
    private final Long id;

    @Schema(description = "닉네임", example = "123")
    private final String nickname;

    public UserResponse(Long id, String nickname) {
        this.id = id;
        this.nickname = nickname;
    }
}
