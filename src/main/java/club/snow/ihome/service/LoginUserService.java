package club.snow.ihome.service;

import club.snow.ihome.bean.dto.UserLoginDTO;
import club.snow.ihome.bean.req.SignInReq;

/**
 * The type LoginUserService.
 *
 * @author <a href="mailto:pengdahai216@126.com">pengdahai</a>
 * @since 2024/4/21
 */
public interface LoginUserService {

    /**
     * Sign in login user.
     *
     * @param signInReq the sign in req
     * @return the login user
     */
    UserLoginDTO signIn(SignInReq signInReq);
}
