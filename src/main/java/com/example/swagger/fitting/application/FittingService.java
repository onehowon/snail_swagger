package com.example.swagger.fitting.application;

import com.example.swagger.fitting.dto.NailCombinationRequest;
import com.example.swagger.fitting.dto.NailCombinationResponse;
import com.example.swagger.fitting.dto.NailStorageItem;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


/**
 * FittingService
 * <p>
 * 가상 피팅 관련 비즈니스 로직을 처리하는 서비스 클래스입니다.
 */
@Service
@RequiredArgsConstructor
public class FittingService {
    /**
     * 사용자 손 이미지 업로드 처리
     *
     * @param imageBytes 업로드된 이미지의 바이트 데이터
     * @return 업로드된 이미지의 URL
     */
    public String uploadImage(byte[] imageBytes) {
        // 이미지 업로드 로직 (S3, Local 등 저장소에 저장)
        return "http://example.com/uploaded-image.png"; // 업로드된 이미지 URL 반환
    }

    /**
     * 업로드된 손 이미지에 가상 디자인 적용
     *
     * @param imageUrl 업로드된 이미지의 URL
     * @param designId 적용할 디자인의 ID
     * @return 가상 디자인이 적용된 이미지의 URL
     */
    public String applyDesign(String imageUrl, Long designId) {
        // 가상 디자인 적용 로직 (AI, OpenCV 등 사용)
        return "http://example.com/virtual-fitted-image.png"; // 가상 피팅 결과 이미지 URL 반환
    }

    /**
     * 네일 조합 적용
     *
     * @param request 네일 디자인 ID 목록
     * @return {@link NailCombinationResponse} 조합된 결과 이미지 URL
     */
    public NailCombinationResponse applyCombination(NailCombinationRequest request) {
        String combinedImageUrl = "http://example.com/combined-nail.png";
        return new NailCombinationResponse(combinedImageUrl);
    }

    private final List<NailStorageItem> storage = new ArrayList<>();

    /**
     * 보관함 목록 조회
     *
     * @return 보관함에 저장된 네일 디자인 목록
     */
    public List<NailStorageItem> getStorage() {
        return storage;
    }

    /**
     * 보관함에 디자인 추가
     *
     * @param item 보관함에 추가할 네일 디자인 정보
     */
    public void addToStorage(NailStorageItem item) {
        storage.add(item);
    }
}
