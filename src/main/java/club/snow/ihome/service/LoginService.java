package club.snow.ihome.service;

import club.snow.ihome.bean.dto.UserLoginDTO;
import club.snow.ihome.bean.req.SignInReq;
import club.snow.ihome.common.enums.SignTypeEnum;
import club.snow.ihome.core.security.EmailPasswordAuthenticationToken;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * The type LoginService.
 *
 * @author <a href="mailto:pengdahai216@126.com">pengdahai</a>
 * @date 2024.6.13
 */
@Service
public class LoginService {

    private static final Logger log = LoggerFactory.getLogger(LoginService.class);
    @Autowired
    private LoginUserService loginUserService;

    @Resource
    private AuthenticationManager authenticationManager;

    /**
     * Sign in user login dto.
     *
     * @param signInReq the sign in req
     * @return the user login dto
     */
    public UserLoginDTO signIn(SignInReq signInReq) {
        checkSignInParam(signInReq);
        UserLoginDTO loginDTO = null;
        try {
            AbstractAuthenticationToken authenticationToken;
            if (Objects.equals(signInReq.getSignInType(), SignTypeEnum.USERNAME.getCode())) {
                authenticationToken = new UsernamePasswordAuthenticationToken(signInReq.getUserName(), signInReq.getPassword());
            } else {
                authenticationToken = new EmailPasswordAuthenticationToken(signInReq.getEmail(), signInReq.getPassword());
            }
            // 该方法会去调用 UserDetailsService.loadUserByUsername
            Authentication authenticate = authenticationManager.authenticate(authenticationToken);
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            loginDTO = (UserLoginDTO) authenticate.getPrincipal();
        } catch (Exception e) {
            log.error("signIn username:{},signIn error:", signInReq.getUserName(), e);
        }
        return loginDTO;
    }

    private void checkSignInParam(SignInReq signInReq) {

    }
}
