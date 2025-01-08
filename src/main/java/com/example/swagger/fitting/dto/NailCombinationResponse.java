package com.example.swagger.fitting.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
@Schema(description = "네일 조합 응답 DTO")
public class NailCombinationResponse {

    /**
     * 조합된 결과 이미지 URL
     */

    @Schema(description = "조합된 결과 이미지 URL", example = "http://example.com/combined-nail.png")
    private final String resultImageUrl;

    public NailCombinationResponse(String resultImageUrl){
        this.resultImageUrl = resultImageUrl;
    }
}
