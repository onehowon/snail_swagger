package com.example.swagger.global;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CommonResponse<T> {
    private final int code;
    private final String message;
    private final T data;

    public static <T> CommonResponse<T> success(T data) {
        return new CommonResponse<>(200, "success", data);
    }

    public static <T> CommonResponse<T> success(){
        return new CommonResponse<>(200, "success", null);
    }
}
