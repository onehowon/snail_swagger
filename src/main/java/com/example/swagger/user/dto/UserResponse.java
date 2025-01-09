package com.example.swagger.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
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

    @Schema(description = "사용자 유형", example = "일반 유저")
    private final String userType;

    @Schema(description = "등록된 IP 주소", example = "192.168.0.1")
    private final String registeredIp;

    @Schema(description = "계정 생성 임시", example = "2025-01-01T00:00:00")
    private final LocalDateTime createdAt;

    @Schema(description = "계정 삭제 일시", example = "2025-01-01T00:00:01")
    private final LocalDateTime deletedAt;

    /**
     * UserResponse 생성자
     *
     * @param id 사용자 ID
     * @param nickname 닉네임
     * @param userType 사용자 유형
     * @param registeredIp 등록된 IP 주소
     * @param createdAt 계정 생성 일시
     * @param deletedAt 계정 삭제 일시
     */
    public UserResponse(Long id, String nickname, String userType, String registeredIp, LocalDateTime createdAt, LocalDateTime deletedAt) {
        this.id = id;
        this.nickname = nickname;
        this.userType = userType;
        this.registeredIp = registeredIp;
        this.createdAt = createdAt;
        this.deletedAt = deletedAt;
    }
}
