package com.example.swagger.nail.api;

import com.example.swagger.global.ErrorResponse;
import com.example.swagger.nail.dto.NailGroupRequest;
import com.example.swagger.nail.dto.NailGroupResponse;
import com.example.swagger.nail.dto.NailResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Nail Group API
 * 네일 그룹 관리
 */
@RestController
@RequestMapping("/nail-group")
public class NailGroupController {

    private final ConcurrentHashMap<Integer, NailGroupResponse> nailGroupDatabase = new ConcurrentHashMap<>();
    private final AtomicInteger nailGroupIdCounter = new AtomicInteger(1);

    /**
     * 네일 그룹 생성
     */
    @Operation(summary = "네일 그룹 생성", description = "새로운 네일 그룹을 생성합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "네일 그룹 생성 성공",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = NailGroupResponse.class))),
            @ApiResponse(responseCode = "400", description = "잘못된 요청 데이터",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping
    public ResponseEntity<?> createNailGroup(@RequestBody NailGroupRequest request) {
        if (request == null || isRequestInvalid(request)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse("유효하지 않은 데이터입니다."));
        }

        int groupId = nailGroupIdCounter.getAndIncrement();
        NailGroupResponse nailGroup = new NailGroupResponse(
                String.valueOf(groupId),
                createDummyNailResponse(request.getNailThumbId(), "thumb"),
                createDummyNailResponse(request.getNailIndexId(), "index"),
                createDummyNailResponse(request.getNailMiddleId(), "middle"),
                createDummyNailResponse(request.getNailRingId(), "ring"),
                createDummyNailResponse(request.getNailPinkyId(), "pinky"),
                LocalDateTime.now()
        );

        nailGroupDatabase.put(groupId, nailGroup);
        return ResponseEntity.status(HttpStatus.CREATED).body(nailGroup);
    }

    /**
     * 네일 그룹 조회
     */
    @Operation(summary = "네일 그룹 조회", description = "ID로 특정 네일 그룹을 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "네일 그룹 조회 성공",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = NailGroupResponse.class))),
            @ApiResponse(responseCode = "404", description = "네일 그룹을 찾을 수 없음",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> getNailGroup(@PathVariable int id) {
        NailGroupResponse nailGroup = nailGroupDatabase.get(id);
        if (nailGroup == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse("네일 그룹을 찾을 수 없습니다."));
        }
        return ResponseEntity.ok(nailGroup);
    }

    /**
     * 네일 그룹 목록 조회
     */
    @Operation(summary = "네일 그룹 목록 조회", description = "모든 네일 그룹을 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "네일 그룹 목록 조회 성공",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = NailGroupResponse[].class)))
    })
    @GetMapping
    public ResponseEntity<?> listNailGroups() {
        List<NailGroupResponse> groups = new ArrayList<>(nailGroupDatabase.values());
        return ResponseEntity.ok(groups);
    }

    /**
     * 네일 그룹 삭제
     */
    @Operation(summary = "네일 그룹 삭제", description = "특정 네일 그룹을 삭제합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "네일 그룹 삭제 성공"),
            @ApiResponse(responseCode = "404", description = "네일 그룹을 찾을 수 없음",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)))
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteNailGroup(@PathVariable int id) {
        if (!nailGroupDatabase.containsKey(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse("네일 그룹을 찾을 수 없습니다."));
        }
        nailGroupDatabase.remove(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * 네일 그룹 저장 해제
     */
    @Operation(summary = "네일 그룹 저장 해제", description = "특정 네일 그룹의 저장을 해제합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "네일 그룹 저장 해제 성공"),
            @ApiResponse(responseCode = "404", description = "네일 그룹을 찾을 수 없음",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping("/{id}/unsave")
    public ResponseEntity<?> unsaveNailGroup(@PathVariable int id) {
        if (!nailGroupDatabase.containsKey(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse("네일 그룹을 찾을 수 없습니다."));
        }
        nailGroupDatabase.remove(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * 요청 데이터 검증
     */
    private boolean isRequestInvalid(NailGroupRequest request) {
        return request.getNailThumbId() == null || request.getNailIndexId() == null ||
                request.getNailMiddleId() == null || request.getNailRingId() == null ||
                request.getNailPinkyId() == null;
    }

    /**
     * 네일 응답 생성
     */
    private NailResponse createDummyNailResponse(Integer id, String type) {
        return new NailResponse(id, "https://example.com/" + type + ".png");
    }
}