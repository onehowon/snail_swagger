package com.example.swagger.fitting.api;

import com.example.swagger.fitting.application.FittingService;
import com.example.swagger.fitting.dto.NailCombinationRequest;
import com.example.swagger.fitting.dto.NailCombinationResponse;
import com.example.swagger.fitting.dto.NailStorageItem;
import com.example.swagger.global.CommonResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.io.IOException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    /**
     * 네일 조합 적용
     *
     * @param request 네일 디자인 ID 목록
     * @return {@link CommonResponse} 조합된 네일 디자인 결과 URL
     */
    @PostMapping("/combinations")
    @Operation(summary = "네일 조합보기", description = "사용자가 선택한 네일 디자인 조합을 적용하고 결과를 반환합니다.")
    @ApiResponse(responseCode = "200", description = "조합 성공", content = @Content(schema = @Schema(implementation = NailCombinationResponse.class)))
    public CommonResponse<NailCombinationResponse> applyCombination(
            @RequestBody NailCombinationRequest request) {
        NailCombinationResponse response = fittingService.applyCombination(request);
        return CommonResponse.success(response);
    }

    /**
     * 네일 보관함 목록 조회
     *
     * @return {@link CommonResponse} 보관함에 저장된 네일 디자인 목록
     */
    @GetMapping("/storage")
    @Operation(summary = "네일 보관함 조회", description = "사용자가 보관함에 저장한 네일 디자인 목록을 조회합니다.")
    @ApiResponse(responseCode = "200", description = "조회 성공", content = @Content(schema = @Schema(implementation = NailStorageItem.class)))
    public CommonResponse<List<NailStorageItem>> getStorage() {
        List<NailStorageItem> storage = fittingService.getStorage();
        return CommonResponse.success(storage);
    }

    /**
     * 네일 보관함에 디자인 저장
     *
     * @param item 보관함에 추가할 네일 디자인 정보
     * @return {@link CommonResponse} 성공 메시지
     */
    @PostMapping("/storage")
    @Operation(summary = "네일 보관함 저장", description = "사용자가 보관함에 네일 디자인을 추가합니다.")
    @ApiResponse(responseCode = "200", description = "저장 성공")
    public CommonResponse<Void> addToStorage(@RequestBody NailStorageItem item) {
        fittingService.addToStorage(item);
        return CommonResponse.success();
    }
}
