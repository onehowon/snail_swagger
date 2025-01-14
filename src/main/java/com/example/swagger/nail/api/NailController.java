package com.example.swagger.nail.api;

import com.example.swagger.global.ErrorResponse;
import com.example.swagger.nail.dto.NailRequest;
import com.example.swagger.nail.dto.NailResponse;
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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Nail API
 * 단일 네일 관리
 */
@RestController
@RequestMapping("/nail")
@Tag(name = "Nail", description = "네일 관련 API")
public class NailController {

    private final Map<Integer, NailResponse> nailDatabase = new ConcurrentHashMap<>();
    private final AtomicInteger nailIdCounter = new AtomicInteger(1);

    /**
     * 네일 생성
     */
    @Operation(summary = "네일 생성", description = "새로운 네일을 생성합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "네일 생성 성공",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = NailResponse.class))),
            @ApiResponse(responseCode = "400", description = "잘못된 요청 데이터",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping
    public ResponseEntity<?> createNail(@RequestBody NailRequest nailRequest) {
        if (nailRequest.getImageUrl() == null || nailRequest.getImageUrl().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse("유효하지 않은 이미지 URL"));
        }

        int id = nailIdCounter.getAndIncrement();
        NailResponse nail = new NailResponse(id, nailRequest.getImageUrl());
        nailDatabase.put(id, nail);
        return ResponseEntity.status(HttpStatus.CREATED).body(nail);
    }

    /**
     * 네일 조회
     */
    @Operation(summary = "네일 조회", description = "ID로 특정 네일을 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "네일 조회 성공",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = NailResponse.class))),
            @ApiResponse(responseCode = "404", description = "네일을 찾을 수 없음",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> getNail(@PathVariable int id) {
        NailResponse nail = nailDatabase.get(id);
        if (nail == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse("네일을 찾을 수 없습니다."));
        }
        return ResponseEntity.ok(nail);
    }

    /**
     * 네일 수정
     */
    @Operation(summary = "네일 수정", description = "기존 네일의 정보를 수정합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "네일 수정 성공",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = NailResponse.class))),
            @ApiResponse(responseCode = "404", description = "네일을 찾을 수 없음",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PutMapping("/{id}")
    public ResponseEntity<?> updateNail(@PathVariable int id, @RequestBody NailRequest nailRequest) {
        NailResponse existingNail = nailDatabase.get(id);
        if (existingNail == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse("네일을 찾을 수 없습니다."));
        }

        NailResponse updatedNail = new NailResponse(id, nailRequest.getImageUrl());
        nailDatabase.put(id, updatedNail);
        return ResponseEntity.ok(updatedNail);
    }

    /**
     * 네일 삭제
     */
    @Operation(summary = "네일 삭제", description = "특정 네일을 삭제합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "네일 삭제 성공"),
            @ApiResponse(responseCode = "404", description = "네일을 찾을 수 없음",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)))
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteNail(@PathVariable int id) {
        if (!nailDatabase.containsKey(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse("네일을 찾을 수 없습니다."));
        }
        nailDatabase.remove(id);
        return ResponseEntity.noContent().build();
    }
}