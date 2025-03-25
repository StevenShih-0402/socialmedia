package com.example.socialmedia.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Component
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)  // 關閉跨站請求偽造 CSRF 的防護 (因為目前使用 Jwt 驗證)
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))  // 啟用跨來源資源共享 CORS 的設定，允許 Vue 存取 Spring Boot 後端 API。
                .build();
    }

    // 設定 CORS 規則
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOriginPattern("*"); // 允許所有來源
        configuration.addAllowedMethod("*"); // 允許所有 HTTP 方法
        configuration.addAllowedHeader("*"); // 允許所有標頭
        configuration.setAllowCredentials(true); // 允許發送 Cookie 或授權資訊 (如 JWT)
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
