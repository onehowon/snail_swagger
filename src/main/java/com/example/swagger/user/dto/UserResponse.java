package com.example.swagger.user.dto;

import com.example.swagger.user.dto.UserDTO.UserMeta;
import com.example.swagger.user.dto.UserDTO.UserPreference;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 사용자 응답 데이터
 */
@Data
@AllArgsConstructor
@Schema(description = "사용자 응답 데이터")
public class UserResponse {

    @Schema(description = "사용자 닉네임", example = "네일리안0001")
    private String nickName;

    @Schema(description = "사용자 타입", example = "CUSTOMER")
    private String userType;

    @Schema(description = "사용자 메타 정보")
    private UserMeta meta;

    @Schema(description = "사용자 선호 정보")
    private UserPreference preference;
}

