package com.example.swagger.user.api;

import com.example.swagger.global.CommonResponse;
import com.example.swagger.user.application.UserService;
import com.example.swagger.user.dto.UserRequest;
import com.example.swagger.user.dto.UserResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
@Tag(name = "User", description = "사용자 관리 API")
public class UserController {
    private final UserService userService;

    /**
     * 사용자 등록 API
     *
     * @param request 사용자 등록 요청 데이터
     * @return 등록된 사용자 정보
     */
    @PostMapping
    @Operation(summary = "사용자 등록", description = "새로운 사용자를 등록합니다.")
    @ApiResponse(responseCode = "200", description = "사용자 등록 성공")
    public CommonResponse<UserResponse> createUser(@RequestBody UserRequest request) {
        UserResponse userResponse = userService.createUser(request);
        return CommonResponse.success(userResponse);
    }

    /**
     * 사용자 정보 조회 API
     *
     * @param id 사용자 ID
     * @return 조회된 사용자 정보
     */
    @GetMapping("/{id}")
    // 추후 토큰 기반 조회
    @Operation(summary = "사용자 정보 조회", description = "사용자 정보를 조회합니다.")
    @ApiResponse(responseCode = "200", description = "사용자 조회 성공")
    @ApiResponse(responseCode = "404", description = "사용자를 찾을 수 없음")
    public CommonResponse<UserResponse> getUser(@PathVariable Long id){
        UserResponse userResponse = userService.getUserById(id);
        return CommonResponse.success(userResponse);
    }
}
