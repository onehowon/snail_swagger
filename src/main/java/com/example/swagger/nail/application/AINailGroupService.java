package com.example.swagger.nail.application;

import com.example.swagger.nail.dto.AINailGroupRequest;
import com.example.swagger.nail.dto.AINailGroupResponse;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service
public class AINailGroupService {

    private final Map<Long, AINailGroupResponse> nailGroups = new HashMap<>();
    private Long groupIdCounter = 1L;

    /**
     * AI Nail Group 생성
     *
     * @param request AI Nail Group 생성 요청 데이터
     * @return 생성된 AI Nail Group 응답 데이터
     */
    public AINailGroupResponse createNailGroup(AINailGroupRequest nailGroupRequest) {
        Long groupId = groupIdCounter++;

        AINailGroupResponse nailGroup = new AINailGroupResponse(
                groupId,
                nailGroupRequest.getFingerThumb(),
                nailGroupRequest.getFingerIndex(),
                nailGroupRequest.getFingerMiddle(),
                nailGroupRequest.getFingerRing(),
                nailGroupRequest.getFingerPinky()
        );

        nailGroups.put(groupId, nailGroup);
        return nailGroup;
    }

    /**
     * AI Nail Group 조회
     *
     * @param groupId AI 네일 그룹 ID
     * @return 조회된 AI Nail Group 응답 데이터
     */
    public AINailGroupResponse getNailGroupById(Long groupId) {
        if(!nailGroups.containsKey(groupId)) {
            throw new RuntimeException("AI Nail Group을 찾을 수 업슴");
        }
        return nailGroups.get(groupId);
    }
}
