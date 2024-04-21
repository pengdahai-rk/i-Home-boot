package club.snow.ihome.common.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;

/**
 * The type EnvironmentUtil.
 *
 * @author <a href="mailto:pengdahai216@126.com">pengdahai</a>
 * @since 2024 /4/21
 */
public class EnvironmentUtil implements EnvironmentAware {

    private static Environment environment;

    @Override
    public void setEnvironment(Environment environment) {
        EnvironmentUtil.environment = environment;
    }

    public static String getProperty(String key) {
        if (StringUtils.isEmpty(key)) {
            return "";
        }
        return environment.getProperty(key);
    }

    public static <T> T getProperty(String key, Class<T> clazz) {
        return environment.getProperty(key, clazz);
    }
}
