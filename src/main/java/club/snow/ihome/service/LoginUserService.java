package club.snow.ihome.service;

import club.snow.ihome.bean.LoginUser;
import club.snow.ihome.bean.req.LoginReq;

/**
 * The type LoginUserService.
 *
 * @author <a href="mailto:pengdahai216@126.com">pengdahai</a>
 * @since 2024/4/21
 */
public interface LoginUserService {

    /**
     * Login login user.
     *
     * @param loginReq the login req
     * @return the login user
     */
    LoginUser login(LoginReq loginReq);
}
