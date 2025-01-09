package com.example.swagger.nail.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
@Schema(description = "AI 네일 요청 DTO")
public class AiNailRequest {

    @Schema(description = "네일 모양", example = "ROUND")
    private final String shape;

    @Schema(description = "네일 색상", example = "RED")
    private final String color;

    @Schema(description = "패턴 정보", example = "STRIPED")
    private final String pattern;

    /**
     * AiNailRequest 생성자
     *
     * @param shape 네일 모양
     * @param color 네일 색상
     * @param pattern 패턴 정보
     */

    public AiNailRequest(String shape, String color, String pattern) {
        this.shape = shape;
        this.color = color;
        this.pattern = pattern;
    }
}
