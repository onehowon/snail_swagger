package com.example.swagger.content.api;

import com.example.swagger.content.application.ContentService;
import com.example.swagger.content.dto.ContentListResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * ContentController
 * <p>
 * 추천 콘텐츠 관련 API를 제공하는 컨트롤러 클래스입니다.
 */
@RestController
@RequestMapping("/contents")
@RequiredArgsConstructor
@Tag(name = "Contents", description = "추천 콘텐츠 API")
public class ContentController {

    private final ContentService contentService;

    /**
     * 추천 콘텐츠 목록 조회
     *
     * @param page 페이지 번호 (0부터 시작)
     * @param size 페이지 크기
     * @return {@link ContentListResponse} 추천 콘텐츠 목록과 총 개수
     */

    @GetMapping("/recommendations")
    @Operation(summary = "추천 콘텐츠 목록", description = "사용자에게 추천 콘텐츠 목록을 반환합니다.")
    @ApiResponse(responseCode = "200", description = "추천 목록 반환 성공")
    @ApiResponse(responseCode = "400", description = "잘못된 요청")
    public ContentListResponse getRecommendations(
            @Parameter(description = "페이지 번호 (0부터 시작)", example = "0") @RequestParam(required = false, defaultValue = "0") Integer page,
            @Parameter(description = "페이지 크기", example = "10") @RequestParam(required = false, defaultValue = "10") Integer size) {
        return contentService.getRecommendations(page, size);
    }
}
