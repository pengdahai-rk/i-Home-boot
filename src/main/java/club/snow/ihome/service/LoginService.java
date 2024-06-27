package club.snow.ihome.service;

import club.snow.ihome.bean.domain.entity.UserInfoDO;
import club.snow.ihome.bean.domain.entity.UserLoginDO;
import club.snow.ihome.bean.dto.UserLoginDTO;
import club.snow.ihome.bean.req.SignInReq;
import club.snow.ihome.bean.req.SignUpReq;
import club.snow.ihome.common.constants.UserConstants;
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
import org.springframework.transaction.support.TransactionTemplate;

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

    @Autowired
    private UserInfoService userInfoService;

    @Resource
    private AuthenticationManager authenticationManager;

    @Autowired
    private TransactionTemplate transactionTemplate;

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
                authenticationToken = new UsernamePasswordAuthenticationToken(signInReq.getUsername(), signInReq.getPassword());
            } else {
                authenticationToken = new EmailPasswordAuthenticationToken(signInReq.getEmail(), signInReq.getPassword());
            }
            // 该方法会去调用 UserDetailsService.loadUserByUsername
            Authentication authenticate = authenticationManager.authenticate(authenticationToken);
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            loginDTO = (UserLoginDTO) authenticate.getPrincipal();
        } catch (Exception e) {
            log.error("signIn username:{},signIn error:", signInReq.getUsername(), e);
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
        UserLoginDO userLoginDO = buildUserLoginDO(signUpReq);
        UserInfoDO userInfoDO = buildUserInfoDO(userLoginDO);
        transactionTemplate.executeWithoutResult((transactionStatus) -> {
            loginUserService.signUpUser(userLoginDO);
            userInfoService.addUserInfo(userInfoDO);
        });
    }

    private UserInfoDO buildUserInfoDO(UserLoginDO userLoginDO) {
        UserInfoDO userInfoDO = new UserInfoDO();
        userInfoDO.setUserId(userLoginDO.getId());
        userInfoDO.setNickname(userLoginDO.getUsername());
        userInfoDO.setAvatar(UserConstants.AVATAR);
        userInfoDO.setHometown(UserConstants.HOMETOWN);
        userInfoDO.setAboutMe(UserConstants.ABOUT_ME);
        userInfoDO.setCreateBy(userLoginDO.getCreateBy());
        userInfoDO.setUpdateBy(userLoginDO.getUpdateBy());
        return userInfoDO;
    }

    private UserLoginDO buildUserLoginDO(SignUpReq signUpReq) {
        UserLoginDO loginDO = new UserLoginDO();
        loginDO.setId(IdUtil.getSnowflakeNextId());
        loginDO.setUsername(signUpReq.getUsername());
        loginDO.setUserType(UserTypeEnum.REGISTER.getCode());
        loginDO.setPassword(SecurityUtil.encryptPassword(signUpReq.getPassword()));
        loginDO.setPhone(signUpReq.getPhone());
        loginDO.setEmail(signUpReq.getEmail());
        loginDO.setSignInIp(NetUtil.getIpAddr(ServletUtil.getRequest()));
        loginDO.setCreateBy(signUpReq.getUsername());
        loginDO.setUpdateBy(signUpReq.getUsername());
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
