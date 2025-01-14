package com.example.swagger.content.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * ContentItem
 * <p>
 * 추천 콘텐츠 항목 DTO 클래스입니다.
 */
@Getter
@AllArgsConstructor
@Schema(description = "추천 콘텐츠 항목 DTO")
public class ContentItem {
    @Schema(description = "콘텐츠 ID", example = "123")
    private final Long id;

    @Schema(description = "콘텐츠 이미지 URL", example = "http://example.com/image.png")
    private final String imageUrl;
}