package com.example.swagger.auth.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;

public class JwtUtil {

    private static final String SECRET_KEY = "mySecretKey";
    private static final long ACCESS_TOKEN_EXPIRATION = 86400000L; // 1일
    private static final long REFRESH_TOKEN_EXPIRATION = 2592000000L; // 30일

    /**
     * 액세스 토큰 생성
     *
     * @param code 인증 코드
     * @return 액세스 토큰
     */
    public static String generateAccessToken(String code) {
        return Jwts.builder()
                .setSubject("accessToken")
                .claim("code", code)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_EXPIRATION))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    /**
     * 리프레시 토큰 생성
     *
     * @param code 인증 코드
     * @return 리프레시 토큰
     */
    public static String generateRefreshToken(String code) {
        return Jwts.builder()
                .setSubject("refreshToken")
                .claim("code", code)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + REFRESH_TOKEN_EXPIRATION))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }
}