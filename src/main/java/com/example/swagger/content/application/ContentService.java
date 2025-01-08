package com.example.swagger.content.application;

import com.example.swagger.content.dto.ContentItem;
import com.example.swagger.content.dto.ContentListResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * ContentService
 * <p>
 * 추천 콘텐츠 관련 비즈니스 로직을 처리하는 서비스 클래스입니다.
 */
@Service
@RequiredArgsConstructor
public class ContentService {
    /**
     * 추천 콘텐츠 목록 조회
     *
     * @param page 페이지 번호 (0부터 시작)
     * @param size 페이지 크기
     * @return {@link ContentListResponse} 추천 콘텐츠 목록과 총 개수
     */

    public ContentListResponse getRecommendations(int page, int size) {
        // 예제 로직: 실제 DB 또는 외부 API를 통해 데이터 조회하는 로직으로 대체 필요
        List<ContentItem> contents = List.of(
                new ContentItem(1L, "추천 콘텐츠 1", "http://example.com/image1.png"),
                new ContentItem(2L, "추천 콘텐츠 2", "http://example.com/image2.png")
        );

        return new ContentListResponse(contents, contents.size());
    }
}
