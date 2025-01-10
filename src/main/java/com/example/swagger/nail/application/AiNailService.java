package com.example.swagger.nail.application;

import com.example.swagger.nail.dto.AINailResponse;
import com.example.swagger.nail.dto.AiNailRequest;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service
public class AiNailService {

    private final Map<Long, AINailResponse> nails = new HashMap<>();
    private Long idCounter = 1L;

    /**
     * AI 네일 저장
     *
     * @param request AI 네일 저장 요청 데이터
     * @return 저장된 AI 네일 응답 데이터
     */
    public AINailResponse saveNail(AiNailRequest request) {
        Long id = idCounter++;

        AINailResponse nail = new AINailResponse(
                id,
                request.getShape(),
                request.getColor(),
                request.getPattern(),
                request.getImage()
        );
        nails.put(id, nail);
        return nail;
    }

    /**
     * AI 네일 조회
     *
     * @param id 네일 ID
     * @return 조회된 AI 네일 응답 데이터
     */
    public AINailResponse getNailById(Long id) {
        if (!nails.containsKey(id)) {
            throw new RuntimeException("AI Nail not found");
        }
        return nails.get(id);
    }
}
