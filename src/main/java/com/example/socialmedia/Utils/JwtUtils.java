package com.example.socialmedia.Utils;

import com.example.socialmedia.exeption.JwtTokenException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.security.Key;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Component
public class JwtUtils {
    private final Key secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private final Set<String> blacklistedTokens = new HashSet<>(); // 黑名單存儲
    private static final long EXPIRATION_TIME = 28800000; // 8 小時

    // 生成 Token
    public String generateToken(Integer userId) {
        return Jwts.builder()
                .claim("userId", userId)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(secretKey)
                .compact();
    }

    // 取出 Token
    public String extractToken(String token) {
        if (token != null && token.startsWith("Bearer ")) {
            return token.substring(7);
        }
        return token;
    }

    // 從 Authorization 標頭(authHeader) 取出 Token
    public String extractTokenFromAuthHeader(){
        ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

        HttpServletRequest request = attrs.getRequest();
        String authHeader = request.getHeader("Authorization");

        // Jwt 不存在或格式錯誤
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new JwtTokenException("Token 不存在或格式錯誤。");
        }

        return extractToken(authHeader);
    }

    // 驗證 Token
    public void validateToken() {
        try {
            String token = extractTokenFromAuthHeader();
            // token 已經包含在黑名單
            if (blacklistedTokens.contains(token)) {
                throw new JwtTokenException("不合法的 Token。");
            }

            Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token);
        } catch (JwtException | IllegalArgumentException e) {
            throw new JwtTokenException("不合法的 Token。");
        }
    }

    public Claims getClaimsFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey) // 使用與生成 Token 相同的密鑰
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // 將 Token 加入黑名單
    public void blacklistToken(String token) {
        blacklistedTokens.add(extractToken(token));
    }

    public Integer getLoginUserId() {

        String token = extractTokenFromAuthHeader();
        Claims claims = getClaimsFromToken(token);

        return claims.get("userId", Integer.class);
    }
}
