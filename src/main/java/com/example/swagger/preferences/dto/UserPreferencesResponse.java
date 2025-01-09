package com.example.swagger.preferences.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
@Schema(description = "사용자 선호도 응답 DTO")
public class UserPreferencesResponse {

    @Schema(description = "사용자 ID", example = "1")
    private final Long userId;

    @Schema(description = "네일 모양 총점", example = "12.5")
    private final float shapeScore;

    @Schema(description = "네일 색상 총점", example = "15.0")
    private final float colorScore;

    @Schema(description = "네일 패턴 총점", example = "11.0")
    private final float patternScore;

    /**
     * UserPreferencesResponse 생성자
     *
     * @param userId 사용자 ID
     * @param shapeScore 네일 모양 총점
     * @param colorScore 네일 색상 총점
     * @param patternScore 네일 패턴 총점
     */

    public UserPreferencesResponse(Long userId, float shapeScore, float colorScore, float patternScore) {
        this.userId = userId;
        this.shapeScore = shapeScore;
        this.colorScore = colorScore;
        this.patternScore = patternScore;
    }
}
