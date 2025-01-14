package com.example.swagger.user.api;


import com.example.swagger.global.CommonResponse;
import com.example.swagger.global.ErrorResponse;
import com.example.swagger.user.dto.UserDTO.UserMeta;
import com.example.swagger.user.dto.UserDTO.UserPreference;
import com.example.swagger.user.dto.UserRequest;
import com.example.swagger.user.dto.UserResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * User API
 * 사용자 관리
 */
@RestController
@RequestMapping("/user")
@Tag(name = "User", description = "유저 관련 API")
public class UserController {

    private final Map<String, UserResponse> userDatabase = new ConcurrentHashMap<>();
    private final AtomicInteger userIdCounter = new AtomicInteger(1);

    /**
     * 사용자 생성
     */
    @Operation(summary = "사용자 생성", description = "새로운 사용자를 생성합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "사용자 생성 성공",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserResponse.class))),
            @ApiResponse(responseCode = "400", description = "잘못된 요청 데이터",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CommonResponse.class)))
    })
    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody UserRequest userRequest) {
        if (userRequest.getNickname() == null || userRequest.getUserType() == null) {
            return ResponseEntity.badRequest()
                    .body(CommonResponse.success("유효하지 않은 요청 데이터입니다."));
        }

        String userId = "nailian_" + userIdCounter.getAndIncrement();
        UserResponse newUser = new UserResponse(
                userRequest.getNickname(),
                userRequest.getUserType(),
                new UserMeta(userRequest.getNickname(), "01012341234", "1"),
                new UserPreference(0.1, 0.1, 0.1)
        );

        userDatabase.put(userId, newUser);
        return ResponseEntity.status(201).body(CommonResponse.success(newUser));
    }

    /**
     * 내 프로필 조회
     */
    @Operation(summary = "내 프로필 조회", description = "현재 로그인된 사용자의 프로필 정보를 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "프로필 조회 성공",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserResponse.class))),
            @ApiResponse(responseCode = "404", description = "프로필을 찾을 수 없음",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CommonResponse.class)))
    })
    @GetMapping("/me")
    public ResponseEntity<?> getMyProfile(@RequestHeader("Authorization") String userId) {
        UserResponse user = userDatabase.get(userId);
        if (user == null) {
            return ResponseEntity.status(404)
                    .body(CommonResponse.success("프로필을 찾을 수 없습니다."));
        }
        return ResponseEntity.ok(CommonResponse.success(user));
    }
}