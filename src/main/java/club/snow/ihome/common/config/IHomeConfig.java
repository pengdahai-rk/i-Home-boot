package club.snow.ihome.common.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * The type Home config.
 *
 * @author <a href="mailto:pengdahai216@126.com">pengdahai</a>
 * @date 2024.06.27
 */
@Order(Integer.MIN_VALUE)
@Component // 优先实例化，防止空指针
@ConfigurationProperties(prefix = "i-home")
public class IHomeConfig {

    @Getter
    private static IHomeToken token;
    @Getter
    private static IHomeThreadPool threadPool;
    @Getter
    private static IHomeAuth auth;
    @Getter
    private static IHomeCaptcha captcha;


    @Getter
    @Setter
    public static class IHomeToken {
        private String header;
        private String secret;
        private Long expireTime;
    }

    @Getter
    @Setter
    public static class IHomeThreadPool {
        private int coreSize;
        private int maxSize;
        private int queueCapacity;
        private int keepAlive;
        private String namePrefix;
    }

    @Getter
    @Setter
    public static class IHomeAuth {
        private String[] ignoreUrl;
    }

    @Getter
    @Setter
    public static class IHomeCaptcha {
        private String type;
    }

    public void setToken(IHomeToken iHomeToken) {
        IHomeConfig.token = iHomeToken;
    }

    public void setThreadPool(IHomeThreadPool threadPool) {
        IHomeConfig.threadPool = threadPool;
    }

    public void setAuth(IHomeAuth auth) {
        IHomeConfig.auth = auth;
    }

    public void setCaptcha(IHomeCaptcha captcha) {
        IHomeConfig.captcha = captcha;
    }
}
