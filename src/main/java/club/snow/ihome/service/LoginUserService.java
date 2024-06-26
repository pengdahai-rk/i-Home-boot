package club.snow.ihome.service;

import club.snow.ihome.bean.domain.entity.UserLoginDO;

/**
 * The type LoginUserService.
 *
 * @author <a href="mailto:pengdahai216@126.com">pengdahai</a>
 * @date 2024.4.21
 */
public interface LoginUserService {

    /**
     * Gets by username.
     *
     * @param username the username
     * @return the by username
     */
    UserLoginDO getByUsername(String username);

    /**
     * Gets by email.
     *
     * @param email the email
     * @return the by email
     */
    UserLoginDO getByEmail(String email);

    /**
     * Sign up user boolean.
     *
     * @param userLoginDO the user login do
     * @return the boolean
     */
    Boolean signUpUser(UserLoginDO userLoginDO);
}
