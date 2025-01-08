package com.example.swagger.fitting.api;

import com.example.swagger.fitting.application.FittingService;
import com.example.swagger.global.CommonResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * FittingController
 * <p>
 * 가상 피팅 관련 API를 제공하는 컨트롤러 클래스입니다.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/fitting")
@Tag(name = "Fitting", description = "가상 피팅 API")
public class FittingController {

    private final FittingService fittingService;

    /**
     * 사용자 손 이미지 업로드
     *
     * @param image 사용자가 업로드한 손 이미지 파일
     * @return {@link CommonResponse} 업로드된 이미지의 URL
     */

    @PostMapping("/upload")
    @Operation(summary = "이미지 업로드", description = "사용자의 손 이미지를 업로드합니다.")
    @ApiResponse(responseCode = "200", description = "이미지 업로드 성공")
    public CommonResponse<String> uploadImage(
            @Parameter(description = "손 이미지 파일", required = true) @RequestParam MultipartFile image) throws IOException {
        // 이미지 업로드 로직 호출
        String uploadedUrl = fittingService.uploadImage(image.getBytes());
        return CommonResponse.success(uploadedUrl);
    }

    /**
     * 가상 디자인 적용
     *
     * @param imageUrl 업로드된 손 이미지의 URL
     * @param designId 적용할 디자인의 ID
     * @return {@link CommonResponse} 가상 피팅 결과 이미지의 URL
     */

    @PostMapping("/apply")
    @Operation(summary = "가상 디자인 적용", description = "업로드된 손 이미지에 디자인을 적용합니다.")
    @ApiResponse(responseCode = "200", description = "가상 디자인 적용 성공")
    public CommonResponse<String> applyDesign(
            @Parameter(description = "업로드된 이미지 URL", required = true) @RequestParam String imageUrl,
            @Parameter(description = "디자인 ID", required = true) @RequestParam Long designId) {
        // 가상 디자인 적용 로직 호출
        String resultUrl = fittingService.applyDesign(imageUrl, designId);
        return CommonResponse.success(resultUrl);
    }
}
