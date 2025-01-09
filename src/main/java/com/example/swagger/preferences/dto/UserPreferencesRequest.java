package com.example.swagger.preferences.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
@Schema(description = "사용자 선호도 요청 DTO")
public class UserPreferencesRequest {

    @Schema(description = "사용자 ID", example = "1")
    private final Long userId;

    @Schema(description = "네일 모양", example = "ROUND")
    private final String shape;

    @Schema(description = "네일 색상", example = "RED")
    private final String color;

    @Schema(description = "패턴", example = "STRIPED")
    private final String pattern;

    @Schema(description = "추가할 점수", example = "4.5")
    private final float score;

    /**
     * UserPreferencesRequest 생성자
     *
     * @param userId 사용자 ID
     * @param shape 네일 모양
     * @param color 네일 색상
     * @param pattern 패턴
     * @param score 추가할 점수
     */
    public UserPreferencesRequest(Long userId, String shape, String color, String pattern, float score) {
        this.userId = userId;
        this.shape = shape;
        this.color = color;
        this.pattern = pattern;
        this.score = score;
    }
}
