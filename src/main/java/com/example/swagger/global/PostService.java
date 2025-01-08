package com.example.swagger.global;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class PostService {

    public PostListReadResponse getPosts(Integer page,
                                         Integer size,
                                         String sort) {
        return null;
    }

    public PostReadResponse getPost(Long id) {
        return null;
    }

    public PostCreateResponse createPost(PostCreateRequest request) {
        return null;
    }

    public void updatePost(Long id, PostUpdateRequest request) {

    }

    public void deletePost(Long id) {
    }
}