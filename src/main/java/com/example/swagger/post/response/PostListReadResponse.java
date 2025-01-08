package com.example.swagger.post.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class PostListReadResponse {
    private final Integer totalPages;
    private final Integer pageNumber;
    private final Integer pageSize;
    private final Long totalElements;
    private final List<Post> posts;

    @Getter
    @RequiredArgsConstructor
    public static class Post{
        private final Long id;
        private final String title;
        private final String author;
        @JsonFormat(shape = Shape.STRING, pattern = "yyyy-mm-dd HH:mm:ss", timezone = "Asia/Seoul")
        private final LocalDateTime createdTime;
    }
}
