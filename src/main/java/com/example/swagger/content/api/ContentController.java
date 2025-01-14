package com.example.swagger.content.api;

import com.example.swagger.content.dto.ContentItem;
import com.example.swagger.content.dto.ContentListResponse;
import com.example.swagger.global.CommonResponse;
import com.example.swagger.global.S3Service;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

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

    private final List<ContentItem> contentDatabase = new ArrayList<>();
    private final AtomicLong contentIdCounter = new AtomicLong(1);

    private final S3Service s3Service; // S3 업로드 서비스 의존성

    /**
     * 콘텐츠 추가 (이미지 업로드)
     *
     * @param file 업로드할 이미지 파일
     * @return 추가된 콘텐츠 정보
     */
    @PostMapping
    @Operation(summary = "콘텐츠 추가", description = "이미지를 업로드하여 새로운 콘텐츠를 추가합니다.")
    @ApiResponse(responseCode = "201", description = "콘텐츠 추가 성공", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ContentItem.class))
    })
    public ResponseEntity<CommonResponse<ContentItem>> addContent(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "이미지 파일",
                    required = true,
                    content = @Content(mediaType = "multipart/form-data",
                            schema = @Schema(type = "string", format = "binary")))
            @RequestParam("file") MultipartFile file) {

        // S3에 파일 업로드
        String imageUrl = s3Service.uploadFile(file);

        // ID 자동 생성
        Long generatedId = contentIdCounter.getAndIncrement();

        ContentItem contentItem = new ContentItem(
                generatedId,
                imageUrl
        );

        return ResponseEntity.status(201).body(CommonResponse.success(contentItem));
    }

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
    public ResponseEntity<CommonResponse<ContentListResponse>> getRecommendations(
            @Parameter(description = "페이지 번호 (0부터 시작)", example = "0") @RequestParam(required = false, defaultValue = "0") Integer page,
            @Parameter(description = "페이지 크기", example = "10") @RequestParam(required = false, defaultValue = "10") Integer size) {

        // 페이지 처리
        int fromIndex = Math.min(page * size, contentDatabase.size());
        int toIndex = Math.min(fromIndex + size, contentDatabase.size());

        List<ContentItem> pagedContents = contentDatabase.subList(fromIndex, toIndex);
        ContentListResponse response = new ContentListResponse(pagedContents);

        return ResponseEntity.ok(CommonResponse.success(response));
    }
}