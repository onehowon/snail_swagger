package com.example.swagger.preferences.application;

import com.example.swagger.preferences.dto.UserPreferencesRequest;
import com.example.swagger.preferences.dto.UserPreferencesResponse;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Service;

/**
 * 사용자 선호도 관련 비즈니스 로직을 처리하는 서비스 클래스
 */
@Service
public class UserPreferencesService {

    private final Map<Long, Map<String, Float>> userPreferences = new HashMap<>();

    /**
     * 사용자 선호도 업데이트
     *
     * @param request 사용자 선호도 요청 데이터
     * @return 업데이트된 선호도 응답 데이터
     */

    public UserPreferencesResponse updatePreferences(UserPreferencesRequest request){
        Long userId = request.getUserId();
        userPreferences.putIfAbsent(userId, initializeScores());

        Map<String, Float> scores = userPreferences.get(userId);
        scores.put("shape", scores.get("shape") + request.getScore());
        scores.put("color", scores.get("color") + request.getScore());
        scores.put("pattern", scores.get("pattern") + request.getScore());

        return new UserPreferencesResponse(
                userId,
                scores.get("shape"),
                scores.get("color"),
                scores.get("pattern")
        );
    }
    /**
     * 사용자 선호도 조회
     *
     * @param userId 사용자 ID
     * @return 조회된 선호도 응답 데이터
     */
    public UserPreferencesResponse getPreferences(Long userId){
        if(!userPreferences.containsKey(userId)){
            throw new RuntimeException("사용자 선호도 존재 X");
        }

        Map<String, Float> scores = userPreferences.get(userId);
        return new UserPreferencesResponse(
                userId,
                scores.get("shape"),
                scores.get("color"),
                scores.get("pattern")
        );
    }
    /**
     * 초기 점수 구조 생성
     *
     * @return 초기화된 점수 구조
     */
    private Map<String, Float> initializeScores() {
        Map<String, Float> initialScores = new HashMap<>();
        initialScores.put("shape", 0.0f);
        initialScores.put("color", 0.0f);
        initialScores.put("pattern", 0.0f);
        return initialScores;
    }
}
