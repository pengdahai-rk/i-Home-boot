package club.snow.ihome.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * The type Spring util.
 *
 * @author <a href="mailto:pengdahai216@126.com">pengdahai</a>
 * @date 2024.06.20
 */
@Slf4j
@Component
public class SpringUtil implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(@NonNull ApplicationContext applicationContext) throws BeansException {
        if (Objects.isNull(SpringUtil.applicationContext)) {
            SpringUtil.applicationContext = applicationContext;
            log.info("Spring Util application context init success!");
        }
    }

    /**
     * Gets bean.
     *
     * @param <T>   the type parameter
     * @param clazz the clazz
     * @return the bean
     */
    public static <T> T getBean(Class<T> clazz) {

        return applicationContext.getBean(clazz);
    }

    /**
     * Gets bean.
     *
     * @param <T>      the type parameter
     * @param beanName the bean name
     * @return the bean
     */
    @SuppressWarnings("unchecked")
    public static <T> T getBean(String beanName) {

        return (T) applicationContext.getBean(beanName);
    }

    /**
     * Gets aop proxy.
     *
     * @param <T>     the type parameter
     * @param invoker the invoker
     * @return the aop proxy
     */
    @SuppressWarnings("unchecked")
    public static <T> T getAopProxy(T invoker) {
        return (T) AopContext.currentProxy();
    }

    /**
     * Get active profiles string [ ].
     *
     * @return the string [ ]
     */
    public static String[] getActiveProfiles() {
        return applicationContext.getEnvironment().getActiveProfiles();
    }
    
}
