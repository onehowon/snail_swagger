package com.example.swagger.nail.api;

import com.example.swagger.global.CommonResponse;
import com.example.swagger.nail.application.AiNailService;
import com.example.swagger.nail.dto.AINailResponse;
import com.example.swagger.nail.dto.AiNailRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
     * AI 네일 저장 API
     *
     * @param request AI 네일 저장 요청 데이터
     * @return 저장된 AI 네일 응답 데이터
     */
    @PostMapping("/save")
    @Operation(summary = "AI 네일 저장", description = "온디바이스에서 생성된 AI 네일 데이터를 서버에 저장합니다.")
    @ApiResponse(responseCode = "200", description = "AI 네일 저장 성공")
    public CommonResponse<AINailResponse> saveNail(@RequestBody AiNailRequest request) {
        AINailResponse response = aiNailService.saveNail(request);
        return CommonResponse.success(response);
    }

    /**
     * AI 네일 조회 API
     *
     * @param id AI 네일 ID
     * @return 조회된 AI 네일 응답 데이터
     */
    @GetMapping("/{id}")
    @Operation(summary = "AI 네일 조회", description = "AI 네일 정보를 조회합니다.")
    @ApiResponse(responseCode = "200", description = "AI 네일 조회 성공")
    public CommonResponse<AINailResponse> getNailById(@PathVariable Long id) {
        AINailResponse response = aiNailService.getNailById(id);
        return CommonResponse.success(response);
    }
}
