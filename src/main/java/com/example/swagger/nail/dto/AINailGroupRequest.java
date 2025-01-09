package com.example.swagger.nail.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
@Schema(description = "AI Nail Group 생성 요청 DTO")
public class AINailGroupRequest {
    /** 엄지 네일 ID */
    @Schema(description = "엄지 네일 ID", example = "1")
    private final Long fingerThumb;

    /** 검지 네일 ID */
    @Schema(description = "검지 네일 ID", example = "2")
    private final Long fingerIndex;

    /** 중지 네일 ID */
    @Schema(description = "중지 네일 ID", example = "3")
    private final Long fingerMiddle;

    /** 약지 네일 ID */
    @Schema(description = "약지 네일 ID", example = "4")
    private final Long fingerRing;

    /** 소지 네일 ID */
    @Schema(description = "소지 네일 ID", example = "5")
    private final Long fingerPinky;

    /**
     * AINailGroupRequest 생성자
     *
     * @param fingerThumb 엄지 네일 ID
     * @param fingerIndex 검지 네일 ID
     * @param fingerMiddle 중지 네일 ID
     * @param fingerRing 약지 네일 ID
     * @param fingerPinky 소지 네일 ID
     */

    public AINailGroupRequest(Long fingerThumb, Long fingerIndex, Long fingerMiddle, Long fingerRing, Long fingerPinky) {
        this.fingerThumb = fingerThumb;
        this.fingerIndex = fingerIndex;
        this.fingerMiddle = fingerMiddle;
        this.fingerRing = fingerRing;
        this.fingerPinky = fingerPinky;
    }
}
