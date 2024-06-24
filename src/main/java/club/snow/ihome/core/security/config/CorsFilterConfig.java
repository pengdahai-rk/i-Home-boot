package club.snow.ihome.core.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * The type Cors filter config.
 *
 * @author <a href="mailto:pengdahai216@126.com">pengdahai</a>
 * @date 2024.06.24
 */
@Configuration
public class CorsFilterConfig {

    @Autowired// 自动装配参数，下方的bean
    @Bean
    public CorsFilter corsFilter(UrlBasedCorsConfigurationSource configurationSource) {
        return new CorsFilter(configurationSource);
    }

    // 也可以将方法内的实现整合到上面的corsFilter方法体内
    @Bean
    public UrlBasedCorsConfigurationSource configurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowCredentials(true);
        // 允许的请求头
        corsConfiguration.addAllowedHeader("*");
        // 允许的方法
        corsConfiguration.addAllowedMethod("*");
        // 允许跨域请求域名
        corsConfiguration.addAllowedOriginPattern("http://localhost*");
        source.registerCorsConfiguration("/**", corsConfiguration);
        return source;
    }

}
