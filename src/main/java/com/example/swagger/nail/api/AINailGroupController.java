package com.example.swagger.nail.api;

import com.example.swagger.global.CommonResponse;
import com.example.swagger.nail.application.AINailGroupService;
import com.example.swagger.nail.dto.AINailGroupRequest;
import com.example.swagger.nail.dto.AINailGroupResponse;
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
@RequestMapping("/ai-nail-group")
@Tag(name="AINailGroup", description = "AI Nail Group 관리 API")
public class AINailGroupController {

    private final AINailGroupService aiNailGroupService;

    /**
     * AI Nail Group 생성 API
     *
     * @param request AI Nail Group 생성 요청 데이터
     * @return 생성된 AI Nail Group 응답 데이터
     */
    @PostMapping("/create")
    @Operation(summary = "AI Nail Group 생성", description = "AI 네일 그룹을 생성합니다.")
    @ApiResponse(responseCode = "200", description = "AI Nail Group 생성 성공")
    public CommonResponse<AINailGroupResponse> createNailGroup(@RequestBody AINailGroupRequest request){
        AINailGroupResponse response = aiNailGroupService.createNailGroup(request);
        return CommonResponse.success(response);
    }

    /**
     * AI Nail Group 조회 API
     *
     * @param groupId AI 네일 그룹 ID
     * @return 조회된 AI Nail Group 응답 데이터
     */
    @GetMapping("/{groupId}")
    @Operation(summary = "AI Nail Group 조회", description = "AI 네일 그룹 정보를 조회합니다.")
    @ApiResponse(responseCode = "200", description = "AI Nail Group 조회 성공")
    public CommonResponse<AINailGroupResponse> getNailGroupById(@PathVariable Long groupId){
        AINailGroupResponse response = aiNailGroupService.getNailGroupById(groupId);
        return CommonResponse.success(response);
    }

}
