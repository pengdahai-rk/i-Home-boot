package club.snow.ihome.service.impl;

import club.snow.ihome.bean.domain.entity.UserInfoDO;
import club.snow.ihome.service.UserInfoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


/**
 * The type User info service impl test.
 *
 * @author <a href="mailto:pengdahai216@126.com">pengdahai</a>
 * @date 2024.06.27
 */
@SpringBootTest
class UserInfoServiceImplTest {

    @Autowired
    private UserInfoService userInfoService;

    @Test
    void addUserInfo() {
        UserInfoDO userInfoDO = new UserInfoDO();
        userInfoDO.setUserId(1L);
        userInfoDO.setAvatar("123456");
        userInfoDO.setHometown("earth");
        userInfoDO.setAboutMe("WHO cares!");
        userInfoDO.setCreateBy("p7i");
        userInfoDO.setUpdateBy("p7i");
        userInfoService.addUserInfo(userInfoDO);
    }
}