package com.example.swagger.user.api;


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
 * User API
 * 사용자 관리
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private final Map<Integer, UserResponse> userDatabase = new ConcurrentHashMap<>();
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
                            schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody UserRequest userRequest) {
        if (userRequest.getNickname() == null || userRequest.getUserType() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse("유효하지 않은 요청 데이터입니다."));
        }

        int userId = userIdCounter.getAndIncrement();
        UserResponse newUser = new UserResponse(
                "네일리안" + String.format("%04d", userId),
                userRequest.getUserType(),
                new UserMeta(userRequest.getNickname(), "01012341234", "1"),
                new UserPreference(0.1f, 0.1f, 0.1f)
        );

        userDatabase.put(userId, newUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }

    /**
     * 사용자 조회
     */
    @Operation(summary = "사용자 조회", description = "ID로 특정 사용자를 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "사용자 조회 성공",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserResponse.class))),
            @ApiResponse(responseCode = "404", description = "사용자를 찾을 수 없음",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> getUser(@PathVariable int id) {
        UserResponse user = userDatabase.get(id);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse("사용자를 찾을 수 없습니다."));
        }
        return ResponseEntity.ok(user);
    }

    /**
     * 사용자 목록 조회
     */
    @Operation(summary = "사용자 목록 조회", description = "모든 사용자를 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "사용자 목록 조회 성공",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserResponse[].class)))
    })
    @GetMapping
    public ResponseEntity<?> listUsers() {
        return ResponseEntity.ok(userDatabase.values());
    }

    /**
     * 사용자 삭제
     */
    @Operation(summary = "사용자 삭제", description = "ID로 특정 사용자를 삭제합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "사용자 삭제 성공"),
            @ApiResponse(responseCode = "404", description = "사용자를 찾을 수 없음",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)))
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable int id) {
        if (!userDatabase.containsKey(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse("사용자를 찾을 수 없습니다."));
        }
        userDatabase.remove(id);
        return ResponseEntity.noContent().build();
    }
}