package club.snow.ihome.service.web;

import club.snow.ihome.bean.domain.entity.UserLoginDO;
import club.snow.ihome.bean.dto.UserLoginDTO;
import club.snow.ihome.bean.req.SignInReq;
import club.snow.ihome.common.enums.SignTypeEnum;
import club.snow.ihome.service.LoginUserService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private LoginUserService loginUserService;

    /**
     * Sign in user login dto.
     *
     * @param signInReq the sign in req
     * @return the user login dto
     */
    public UserLoginDTO signIn(SignInReq signInReq) {
        checkSignInParam(signInReq);
        UserLoginDO userLoginDO = null;
        if (Objects.equals(signInReq.getSignInType(), SignTypeEnum.USERNAME.getCode())) {
            userLoginDO = loginUserService.getByUsername(signInReq.getUserName());
        } else if (Objects.equals(signInReq.getSignInType(), SignTypeEnum.EMAIL.getCode())) {
            userLoginDO = loginUserService.getByEmail(signInReq.getEmail());
        }
        if (Objects.isNull(userLoginDO)) {
            
        }

        return null;
    }

    private void checkSignInParam(SignInReq signInReq) {

    }
}
