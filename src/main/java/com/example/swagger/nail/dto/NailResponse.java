package com.example.swagger.nail.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * NailResponse
 * 네일 응답 데이터
 */
@Data
@AllArgsConstructor
@Schema(description = "단일 네일 응답 데이터")
public class NailResponse {

    @Schema(description = "네일 ID", example = "1")
    private int id; // int 타입으로 수정

    @Schema(description = "네일 이미지 URL", example = "https://example.com/nail-image.png")
    private String imageUrl;
}