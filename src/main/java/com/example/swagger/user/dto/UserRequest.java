package com.example.swagger.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Getter;

/**
 * 사용자 생성 요청 데이터
 */
@Data
@Schema(description = "사용자 생성 요청 데이터")
public class UserRequest {

    @Schema(description = "사용자 닉네임", example = "nailianUser", required = true)
    private String nickname;

    @Schema(description = "사용자 타입", example = "CUSTOMER", required = true)
    private String userType;
}
