package com.example.swagger.content.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "콘텐츠 추가 요청 DTO")
public class ContentItemRequest {

    @Schema(description = "콘텐츠 이미지 URL", example = "http://example.com/image.png")
    private String imageUrl;
}
