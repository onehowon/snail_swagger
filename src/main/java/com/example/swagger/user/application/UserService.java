package com.example.swagger.user.application;

import com.example.swagger.user.dto.UserRequest;
import com.example.swagger.user.dto.UserResponse;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 사용자 관련 비즈니스 로직을 처리하는 서비스 클래스
 */
@Service
public class UserService {

    /** 사용자 데이터를 저장하는 메모리 내 리스트 (임시) */
    private final Map<Long, UserResponse> users = new HashMap<>();
    private Long idCounter = 1L;

    /**
     * 사용자 등록
     *
     * @param request 사용자 등록 요청 데이터
     * @return 등록된 사용자 정보
     */

    public UserResponse createUser(UserRequest request) {
        LocalDateTime now = LocalDateTime.now();

        UserResponse newUser = new UserResponse(
                idCounter,
                request.getNickname(),
                request.getUserType(),
                request.getRegisteredIp(),
                now,
                null
        );
        users.put(idCounter++, newUser);
        return newUser;
    }

    /**
     * 사용자 정보 조회
     *
     * @param id 사용자 ID
     * @return 조회된 사용자 정보
     */
    public UserResponse getUserById(Long id) {
        if(!users.containsKey(id)){
            throw new RuntimeException("사용자를 찾을 수 없음");
        }
        return users.get(id);
    }
}
