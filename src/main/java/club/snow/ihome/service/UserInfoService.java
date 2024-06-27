package club.snow.ihome.service;

import club.snow.ihome.bean.domain.entity.UserInfoDO;

/**
 * The interface User info service.
 *
 * @author <a href="mailto:pengdahai216@126.com">pengdahai</a>
 * @date 2024.06.26
 */
public interface UserInfoService {

    /**
     * Add user info long.
     *
     * @param userInfoDO the user info do
     * @return the long
     */
    long addUserInfo(UserInfoDO userInfoDO);
}
