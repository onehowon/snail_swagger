package com.example.swagger.fitting.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * NailStorageItem
 * <p>
 * 네일 보관함 항목 DTO 클래스입니다.
 */
@Getter
@AllArgsConstructor
@Schema(description = "네일 보관함 항목 DTO")
public class NailStorageItem {

    /**
     * 네일 디자인 ID
     */
    @Schema(description = "네일 디자인 ID", example = "1")
    private final Long id;

    @Schema(description = "네일 디자인 이미지 URL", example ="http://example.com/nail1.png" )
    private final String imageUrl;

    @Schema(description = "네일 디자인 이름", example = "프렌치 네일 디자인")
    private final String name;
}
