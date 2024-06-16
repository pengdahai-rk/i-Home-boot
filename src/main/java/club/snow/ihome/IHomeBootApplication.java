package club.snow.ihome;

import club.snow.ihome.bean.domain.entity.UserInfoDO;
import club.snow.ihome.common.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.redisson.api.RLock;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

import java.util.Date;



/**
 * The type Home boot application.
 *
 * @author <a href="mailto:pengdahai216@126.com">pengdahai</a>
 * @since 2024 /06/13
 */
@Slf4j
@MapperScan("club.snow.ihome.dao")
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class}) // 排除数据库,redisson RedissonAutoConfigurationV2.class自动配置
public class IHomeBootApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(IHomeBootApplication.class, args);
        ConfigurableEnvironment environment = run.getEnvironment();
        log.info("i-Home start success! {}", environment.getClass());
        RedisUtil redisUtil = (RedisUtil)run.getBean("redisUtil");

        redisUtil.lock("test","123123");

        RLock test = redisUtil.getLock("test", "123123");

        String name = test.getName();

        System.out.println(name);

        UserInfoDO userInfoDO = new UserInfoDO();
        userInfoDO.setUserId(0L);
        userInfoDO.setAvatar("123123");
        userInfoDO.setGender(0);
        userInfoDO.setBirthday(new Date());
        userInfoDO.setHometown("libai");
        userInfoDO.setAboutMe("12312312");
        userInfoDO.setId(0L);
        userInfoDO.setCreateTime(new Date());
        userInfoDO.setCreateBy("李白");
        userInfoDO.setUpdateTime(new Date());
        userInfoDO.setUpdateBy("");


        redisUtil.setCacheObject("testKey", userInfoDO);


    }

}

