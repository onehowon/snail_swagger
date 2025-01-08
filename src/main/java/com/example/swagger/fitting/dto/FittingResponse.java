package com.example.swagger.fitting.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

/**
 * FittingResponse
 * <p>
 * 가상 피팅 응답 DTO 클래스입니다.
 * <p>
 * 가상 디자인 적용 결과의 URL 정보를 제공합니다.
 */
@Getter
@Schema(description = "가상 피팅 응답 DTO")
public class FittingResponse {
    @Schema(description = "가상 피팅 결과 이미지 URL", example = "http://example.com/virtual-fitted-image.png")
    private final String fittedImageUrl;

    public FittingResponse(String fittedImageUrl) {
        this.fittedImageUrl = fittedImageUrl;
    }
}