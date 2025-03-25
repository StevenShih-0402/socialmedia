package com.example.socialmedia.Utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordUtils {

    //  Spring Security 提供的密碼編碼器，基於 BCrypt 演算法進行單向加密。
    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    // BCryptPasswordEncoder 內建自動加鹽機制
    public static String hashPassword(String password) {
        return encoder.encode(password);
    }

    // 比對密碼
    public static boolean verifyPassword(String rawPassword, String hashedPassword) {
        return encoder.matches(rawPassword, hashedPassword);
    }
}
