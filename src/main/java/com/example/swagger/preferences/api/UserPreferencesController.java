package com.example.swagger.preferences.api;

import com.example.swagger.global.CommonResponse;
import com.example.swagger.preferences.application.UserPreferencesService;
import com.example.swagger.preferences.dto.UserPreferencesRequest;
import com.example.swagger.preferences.dto.UserPreferencesResponse;
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
@RequestMapping("/preferences")
@RequiredArgsConstructor
@Tag(name = "UserPreferences", description = "사용자 선호도 API")
public class UserPreferencesController {

    private final UserPreferencesService userPreferencesService;

    /**
     * 사용자 선호도 업데이트 API
     *
     * @param request 사용자 선호도 업데이트 요청 데이터
     * @return 업데이트된 선호도 응답 데이터
     */
    @PostMapping("/update")
    @Operation(summary = "선호도 업데이트", description = "사용자의 선호도를 업데이트합니다.")
    @ApiResponse(responseCode = "200", description = "선호도 업데이트 성공")
    public CommonResponse<UserPreferencesResponse> updatePreferences(@RequestBody UserPreferencesRequest request) {
        UserPreferencesResponse response = userPreferencesService.updatePreferences(request);
        return CommonResponse.success(response);
    }

    /**
     * 사용자 선호도 조회 API
     *
     * @param userId 사용자 ID
     * @return 조회된 선호도 응답 데이터
     */
    @GetMapping("/{userId}")
    @Operation(summary = "선호도 조회", description = "사용자의 선호도를 조회합니다.")
    @ApiResponse(responseCode = "200", description = "선호도 조회 성공")
    public CommonResponse<UserPreferencesResponse> getPreferences(@PathVariable Long userId) {
        UserPreferencesResponse response = userPreferencesService.getPreferences(userId);
        return CommonResponse.success(response);

    }
}
