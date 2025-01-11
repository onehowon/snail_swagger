package com.example.swagger.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 사용자 메타 데이터
 */
@Data
@AllArgsConstructor
@Schema(description = "사용자 메타 데이터")
public class UserMeta {

    @Schema(description = "사용자 이름", example = "이은학")
    private String legalName;

    @Schema(description = "전화번호", example = "01012341234")
    private String phoneNumber;

    @Schema(description = "성별 (1: 남성, 2: 여성)", example = "1")
    private String gender;
}