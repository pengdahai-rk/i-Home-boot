package club.snow.ihome.core.security.config;

import club.snow.ihome.common.config.IHomeConfig;
import club.snow.ihome.common.filter.JwtAuthenticationTokenFilter;
import club.snow.ihome.core.security.CustomizeAccessDeniedHandler;
import club.snow.ihome.core.security.CustomizeAuthenticationEntryPoint;
import club.snow.ihome.core.security.EmailAuthenticationProvider;
import club.snow.ihome.core.security.handler.LogoutSuccessHandlerImpl;
import club.snow.ihome.core.security.service.EmailUserDetailsService;
import club.snow.ihome.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.DefaultAuthenticationEventPublisher;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.*;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.web.filter.CorsFilter;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Security config.
 *
 * @author <a href="mailto:pengdahai216@126.com">pengdahai</a>
 * @date 2024.06.23
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    private final String[] ignoreUrl = IHomeConfig.getAuth().getIgnoreUrl();
    @Autowired
    private TokenService tokenService;
    @Autowired
    private LogoutSuccessHandlerImpl logoutSuccessHandler;
    @Autowired
    private CorsFilter corsFilter;
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private EmailUserDetailsService emailUserDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((authorizeHttpReq) -> authorizeHttpReq
                        .requestMatchers(ignoreUrl).permitAll()// 登录接口放行
                        .anyRequest().authenticated())
                .csrf(AbstractHttpConfigurer::disable)// CSRF跨域禁用，因为不使用session
                // 基于token，不通过session创建管理SecurityContextHolder
                .sessionManagement(SessionManagementConfigurer::disable)
                .exceptionHandling(exception -> exception
                        .authenticationEntryPoint(new CustomizeAuthenticationEntryPoint()) // 未登录接口校验
                        .accessDeniedHandler(new CustomizeAccessDeniedHandler()))// 无权限接口
                // 添加退出登录接口
                .logout((logout) -> logout.logoutUrl("/api/user/sign-out").logoutSuccessHandler(logoutSuccessHandler))
                // 禁用缓存
                .requestCache(RequestCacheConfigurer::disable)
                // 禁用basic明文验证
                .httpBasic(HttpBasicConfigurer::disable)
                // 禁用默认登录页
                .formLogin(FormLoginConfigurer::disable)
                // 添加filter并控制顺序
                .addFilterBefore(jwtAuthenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(corsFilter, JwtAuthenticationTokenFilter.class)
                .addFilterBefore(corsFilter, LogoutFilter.class);
        return http.build();
    }

    @Bean
    public WebSecurityCustomizer ignoringCustomizer() {
        // 解决登录接口走 jwtAuthenticationTokenFilter等自定义filter
        return (web) -> web.ignoring().requestMatchers(ignoreUrl);
    }

    public JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter() {
        return new JwtAuthenticationTokenFilter(tokenService);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(PasswordEncoder passwordEncoder) {
        List<AuthenticationProvider> providerList = new ArrayList<>();
        // 用户名密码
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        // 邮箱密码
        EmailAuthenticationProvider emailAuthenticationProvider = new EmailAuthenticationProvider(emailUserDetailsService);
        emailAuthenticationProvider.setPasswordEncoder(passwordEncoder);

        providerList.add(daoAuthenticationProvider);
        providerList.add(emailAuthenticationProvider);
        ProviderManager providerManager = new ProviderManager(providerList);
        providerManager.setAuthenticationEventPublisher(new DefaultAuthenticationEventPublisher(applicationEventPublisher));
        return providerManager;
    }
}
