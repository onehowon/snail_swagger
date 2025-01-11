package com.example.swagger.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

public class UserDTO {
    @Data
    @AllArgsConstructor
    public static class UserRequest {
        private String nickname;
        private String userType;
    }

    @Data
    @AllArgsConstructor
    public static class UserResponse {
        private String nickName;
        private String userType;
        private UserMeta meta;
        private UserPreference preference;
    }

    @Data
    @AllArgsConstructor
    public static class UserMeta {
        private String legalName;
        private String phoneNumber;
        private String gender;
    }

    @Data
    @AllArgsConstructor
    public static class UserPreference {
        private double color;
        private double shape;
        private double pattern;
    }
}