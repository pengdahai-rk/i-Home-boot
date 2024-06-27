package club.snow.ihome;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

/**
 * The type Home boot application.
 *
 * @author <a href="mailto:pengdahai216@126.com">pengdahai</a>
 * @date 2024 /06/13
 */
@Slf4j
@MapperScan("club.snow.ihome.dao")
@ConfigurationPropertiesScan(value = {"club.snow.ihome.common.config"})
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
// 排除数据库,redisson RedissonAutoConfigurationV2.class,SecurityAutoConfiguration.class\ManagementWebSecurityAutoConfiguration.class自动配置
public class IHomeBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(IHomeBootApplication.class, args);
        log.info("i-Home-app start success!");
    }

}


