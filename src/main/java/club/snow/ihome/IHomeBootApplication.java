package club.snow.ihome;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

@Slf4j
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class }) // 排除数据库自动配置
public class IHomeBootApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(IHomeBootApplication.class, args);
        ConfigurableEnvironment environment = run.getEnvironment();
        log.info("i-Home start success! ", environment.getConversionService());
    }

}
