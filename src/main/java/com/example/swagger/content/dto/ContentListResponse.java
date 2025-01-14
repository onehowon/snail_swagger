package com.example.swagger.content.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * ContentListResponse
 * <p>
 * 추천 콘텐츠 목록 응답 DTO 클래스입니다.
 */
@Getter
@AllArgsConstructor
@Schema(description = "추천 콘텐츠 목록 응답 DTO")
public class ContentListResponse {
    @Schema(description = "추천 콘텐츠 항목 리스트")
    private final List<ContentItem> contents;
}


