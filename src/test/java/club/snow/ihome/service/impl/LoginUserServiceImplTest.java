package club.snow.ihome.service.impl;

import club.snow.ihome.bean.domain.entity.UserLoginDO;
import club.snow.ihome.service.LoginUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LoginUserServiceImplTest {
    @Autowired
    private LoginUserService loginUserService;

    @Test
    void getByUsername() {
        UserLoginDO userLoginDO = loginUserService.getByUsername("p7i");

        System.out.println(userLoginDO);
    }

    @Test
    void getByEmail() {
        UserLoginDO userLoginDO = loginUserService.getByEmail("pengdahai216@126.com");

        System.out.println(userLoginDO);
    }
}