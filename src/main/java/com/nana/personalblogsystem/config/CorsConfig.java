package com.nana.personalblogsystem.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 *@author nana
 *@version v1.0.0
 *@since v1.0.0
 */

@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOrigin("http://localhost:5173"); // 允许来源
        config.setAllowCredentials(true); // 允许携带cookie
        config.addAllowedMethod("*"); // 允许所有请求方法
        config.addAllowedHeader("*"); // 允许所有头信息

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config); // 适用于所有请求路径

        return new CorsFilter(source);
    }
}
