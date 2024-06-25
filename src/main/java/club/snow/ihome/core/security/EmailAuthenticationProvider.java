package club.snow.ihome.core.security;

import club.snow.ihome.common.enums.BusinessInfoEnum;
import club.snow.ihome.common.exception.BusinessException;
import club.snow.ihome.core.security.service.EmailUserDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.core.authority.mapping.NullAuthoritiesMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.Assert;

import java.util.Objects;

/**
 * The type Email authentication provider.
 *
 * @author <a href="mailto:pengdahai216@126.com">pengdahai</a>
 * @date 2024.06.25
 */
@Slf4j
public class EmailAuthenticationProvider implements AuthenticationProvider, InitializingBean {

    private final GrantedAuthoritiesMapper authoritiesMapper = new NullAuthoritiesMapper();

    private PasswordEncoder passwordEncoder;

    private final EmailUserDetailsService userDetailsService;

    public EmailAuthenticationProvider(EmailUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = determineEmail(authentication);
        UserDetails loadedUser = this.getUserDetailsService().loadUserByEmail(email);
        if (Objects.isNull(loadedUser)) {
            throw new BusinessException(BusinessInfoEnum.USER_SING_NOT_EXIST);
        }
        additionalAuthenticationChecks(loadedUser, (EmailPasswordAuthenticationToken) authentication);

        Object principalToReturn = loadedUser.getUsername();
        return createSuccessAuthentication(principalToReturn, authentication, loadedUser);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return EmailPasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }

    public Authentication createSuccessAuthentication(Object principal, Authentication authentication, UserDetails user) {
        EmailPasswordAuthenticationToken result = EmailPasswordAuthenticationToken.authenticated(principal, authentication.getCredentials(), this.authoritiesMapper.mapAuthorities(user.getAuthorities()));
        result.setDetails(authentication.getDetails());
        return result;
    }

    @SuppressWarnings("deprecation")
    protected void additionalAuthenticationChecks(UserDetails userDetails, EmailPasswordAuthenticationToken authentication) throws AuthenticationException {
        if (authentication.getCredentials() == null) {
            log.debug("Failed to authenticate since no credentials provided");
            throw new BusinessException(BusinessInfoEnum.FAIL);
        }
        String presentedPassword = authentication.getCredentials().toString();
        if (!this.passwordEncoder.matches(presentedPassword, userDetails.getPassword())) {
            log.debug("Failed to authenticate since password does not match stored value");
            throw new BusinessException(BusinessInfoEnum.FAIL);
        }
    }

    @Override
    public final void afterPropertiesSet() {
        Assert.notNull(this.userDetailsService, "userDetailsService must not be null");
    }

    protected EmailUserDetailsService getUserDetailsService() {
        return this.userDetailsService;
    }

    private String determineEmail(Authentication authentication) {
        return (authentication.getPrincipal() == null) ? "NONE_PROVIDED" : authentication.getName();
    }

    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
}
