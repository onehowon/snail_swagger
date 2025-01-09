package com.example.swagger.user.application;

import com.example.swagger.user.dto.UserRequest;
import com.example.swagger.user.dto.UserResponse;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 사용자 관련 비즈니스 로직을 처리하는 서비스 클래스
 */
@Service
public class UserService {

    /** 사용자 데이터를 저장하는 메모리 내 리스트 (임시) */
    private final List<UserResponse> users = new ArrayList<>();

    /**
     * 사용자 등록
     *
     * @param request 사용자 등록 요청 데이터
     * @return 등록된 사용자 정보
     */

    public UserResponse createUser(UserRequest request) {
        // 임시 사용자 ID 생성
        Long userId = (long) (users.size() + 1);
        UserResponse user = new UserResponse(userId, request.getNickname());
        users.add(user);
        return user;
    }

    /**
     * 사용자 정보 조회
     *
     * @param id 사용자 ID
     * @return 조회된 사용자 정보
     */
    public UserResponse getUserById(Long id) {
        return users.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}
