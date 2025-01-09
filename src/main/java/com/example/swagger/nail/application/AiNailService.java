package com.example.swagger.nail.application;

import com.example.swagger.nail.dto.AiNailRequest;
import org.springframework.stereotype.Service;

@Service
public class AiNailService {

    /**
     * AI 네일 조합 생성
     *
     * @param request 네일 조합 요청 데이터
     * @return 생성된 AI 네일 그룹 ID (예시)
     */
    public Long createNailGroup(AiNailRequest aiNailRequest){
        return 100L;
        // 임시 로직
    }
}
