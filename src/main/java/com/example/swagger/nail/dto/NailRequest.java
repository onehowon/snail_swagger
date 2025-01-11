package com.example.swagger.nail.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * NailRequest
 * 단일 네일 생성 및 수정 요청 데이터
 */
@Data
@Schema(description = "단일 네일 생성 및 수정 요청 데이터")
public class NailRequest {

    @Schema(description = "네일 이미지 URL", example = "https://example.com/nail-image.png", required = true)
    private String imageUrl;
}
