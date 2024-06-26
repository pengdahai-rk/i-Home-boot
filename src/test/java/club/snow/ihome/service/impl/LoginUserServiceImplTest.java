package club.snow.ihome.service.impl;

import club.snow.ihome.bean.domain.entity.UserLoginDO;
import club.snow.ihome.common.enums.YesOrNoEnum;
import club.snow.ihome.common.utils.IdUtil;
import club.snow.ihome.service.LoginUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;


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

    @Test
    void signUpUser() {
        UserLoginDO userLoginDO = new UserLoginDO();
        userLoginDO.setId(IdUtil.getSnowflakeNextId());
        userLoginDO.setIsActive(YesOrNoEnum.NO.getCode());
        userLoginDO.setUserName("test");
        userLoginDO.setPassword("$2a$10$2EJFiT7gxjQ2kLLEFngLsuvkUWGgEb1bm26KiEpFowmAQH8Qe1h9e");
        userLoginDO.setPhone("18702715850");
        userLoginDO.setEmail("1001");
        userLoginDO.setSignInDate(new Date());
        userLoginDO.setSignInIp("");
        userLoginDO.setPwdUpdateDate(new Date());
        userLoginDO.setCreateTime(new Date());
        userLoginDO.setCreateBy("");
        userLoginDO.setUpdateTime(new Date());
        userLoginDO.setUpdateBy("");
        Boolean b = loginUserService.signUpUser(userLoginDO);
    }

}