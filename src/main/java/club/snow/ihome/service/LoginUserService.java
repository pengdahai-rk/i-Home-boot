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
}
