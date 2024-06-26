package club.snow.ihome.service;

import club.snow.ihome.bean.domain.entity.UserLoginDO;
import club.snow.ihome.bean.dto.UserLoginDTO;
import club.snow.ihome.bean.req.SignInReq;
import club.snow.ihome.bean.req.SignUpReq;
import club.snow.ihome.common.enums.SignTypeEnum;
import club.snow.ihome.common.enums.UserTypeEnum;
import club.snow.ihome.common.utils.IdUtil;
import club.snow.ihome.common.utils.NetUtil;
import club.snow.ihome.common.utils.SecurityUtil;
import club.snow.ihome.common.utils.ServletUtil;
import club.snow.ihome.core.security.EmailPasswordAuthenticationToken;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;


/**
 * The type LoginService.
 *
 * @author <a href="mailto:pengdahai216@126.com">pengdahai</a>
 * @date 2024.6.13
 */
@Slf4j
@Service
public class LoginService {

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

    /**
     * Sign up.
     *
     * @param signUpReq the sign-up req
     */
    public void signUp(SignUpReq signUpReq) {
        checkSignUpParam(signUpReq);
        long id = IdUtil.getSnowflakeNextId();
        UserLoginDO userLoginDO = buildUserLoginDO(signUpReq, id);
        loginUserService.signUpUser(userLoginDO);
    }

    private UserLoginDO buildUserLoginDO(SignUpReq signUpReq, long id) {
        UserLoginDO loginDO = new UserLoginDO();
        loginDO.setId(id);
        loginDO.setUserName(signUpReq.getUserName());
        loginDO.setUserType(UserTypeEnum.REGISTER.getCode());
        loginDO.setPassword(SecurityUtil.encryptPassword(signUpReq.getPassword()));
        loginDO.setPhone(signUpReq.getPhone());
        loginDO.setEmail(signUpReq.getEmail());
        loginDO.setSignInDate(new Date());
        loginDO.setSignInIp(NetUtil.getIpAddr(ServletUtil.getRequest()));
        loginDO.setPwdUpdateDate(new Date());
        loginDO.setCreateBy(signUpReq.getUserName());
        loginDO.setUpdateBy(signUpReq.getUserName());
        return loginDO;
    }

    private void checkSignUpParam(SignUpReq signUpReq) {

    }

    /**
     * Sign out.
     */
    public void signOut() {

    }
}
