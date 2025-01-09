package com.example.swagger.nail.api;

import com.example.swagger.global.CommonResponse;
import com.example.swagger.nail.application.AiNailService;
import com.example.swagger.nail.dto.AiNailRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ai-nail")
@Tag(name = "AiNail", description = "AI 네일 관리 API")
public class AiNailController {

    private final AiNailService aiNailService;

    /**
     * AI 네일 조합 생성 API
     *
     * @param request 네일 조합 요청 데이터
     * @return 생성된 AI 네일 그룹 ID
     */

    @PostMapping("/create")
    @Operation(summary = "AI 네일 조합 생성", description = "사용자가 요청한 AI 네일 조합")
    @ApiResponse(responseCode = "200", description = "AI 네일 조합 생성 성공")
    public CommonResponse<Long> createAiNailGroup(@RequestBody AiNailRequest request){
        Long groupId = aiNailService.createNailGroup(request);
        return CommonResponse.success(groupId);
    }
}
