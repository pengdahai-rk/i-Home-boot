package club.snow.ihome.core.security.config;

import club.snow.ihome.common.filter.JwtAuthenticationTokenFilter;
import club.snow.ihome.core.security.handler.LogoutSuccessHandlerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * The type Security config.
 *
 * @author <a href="mailto:pengdahai216@126.com">pengdahai</a>
 * @date 2024.06.23
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private LogoutSuccessHandlerImpl logoutSuccessHandler;
    @Autowired
    private CorsFilter corsFilter;
    @Autowired
    private JwtAuthenticationTokenFilter authenticationTokenFilter;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((authorizeHttpReq) -> {
                    authorizeHttpReq.requestMatchers("/i-home/api/user/sign-in").permitAll()// 登录接口放行
                            .anyRequest().authenticated();
                }).csrf(AbstractHttpConfigurer::disable)// CSRF跨域禁用，因为不使用session
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))// 基于token，不通过session创建管理SecurityContextHolder
                .logout((logout) -> logout.logoutUrl("/i-home/api/user/sign-out").logoutSuccessHandler(logoutSuccessHandler))// 添加退出登录接口
                // 添加filter并控制顺序
                .addFilterBefore(authenticationTokenFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(corsFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(corsFilter, LogoutFilter.class);
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

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
