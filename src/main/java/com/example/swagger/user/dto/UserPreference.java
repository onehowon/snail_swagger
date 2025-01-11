package com.example.swagger.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 사용자 선호 정보
 */
@Data
@AllArgsConstructor
@Schema(description = "사용자 선호 정보")
public class UserPreference {

    @Schema(description = "색상 선호도", example = "0.1")
    private float color;

    @Schema(description = "모양 선호도", example = "0.1")
    private float shape;

    @Schema(description = "패턴 선호도", example = "0.1")
    private float pattern;
}