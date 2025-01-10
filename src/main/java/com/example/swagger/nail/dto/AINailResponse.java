package com.example.swagger.nail.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
@Schema(description = "AI 네일 응답 DTO")
public class AINailResponse {

    @Schema(description = "네일 ID", example = "1")
    private final Long id;

    @Schema(description = "네일 모양", example = "ROUND")
    private final String shape;

    @Schema(description = "네일 색상", example = "RED")
    private final String color;

    @Schema(description = "네일 패턴", example = "STRIPED")
    private final String pattern;

    @Schema(description = "네일 이미지 URL", example = "http://example.com/image.png")
    private final String image;

    /**
     * AINailResponse 생성자
     *
     * @param id 네일 ID
     * @param shape 네일 모양
     * @param color 네일 색상
     * @param pattern 네일 패턴
     * @param image 이미지 URL
     */
    public AINailResponse(Long id, String shape, String color, String pattern, String image) {
        this.id = id;
        this.shape = shape;
        this.color = color;
        this.pattern = pattern;
        this.image = image;
    }
}
