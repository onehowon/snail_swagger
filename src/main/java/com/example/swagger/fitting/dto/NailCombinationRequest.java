package com.example.swagger.fitting.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Getter;

@Getter
@Schema(description = "네일 조합 요청 DTO")
public class NailCombinationRequest {

    /**
     * 네일 디자인 ID 목록
     */

    @Schema(description = "네일 디자인 ID 목록", example = "[1,2,3,4,5]")
    private final List<Long> designIds;

    public NailCombinationRequest(List<Long> designIds) {
        this.designIds = designIds;
    }
}
