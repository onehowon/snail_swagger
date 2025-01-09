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

    @Schema(description = "사용자 유형", example = "일반 유저")
    private final String userType;

    @Schema(description = "등록된 IP 주소", example = "192.168.0.1")
    private final String registeredIp;

    /**
     * UserRequest 생성자
     *
     * @param nickname 닉네임
     * @param userType 사용자 유형
     * @param registeredIp 등록된 IP 주소
     */

    public UserRequest(String nickname, String userType, String registeredIp) {
        this.nickname = nickname;
        this.userType = userType;
        this.registeredIp = registeredIp;
    }
}
