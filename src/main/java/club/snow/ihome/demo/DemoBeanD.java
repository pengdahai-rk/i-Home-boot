package club.snow.ihome.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.stereotype.Component;

/**
 * The type DemoBeanD.
 *
 * @author <a href="mailto:pengdahai216@126.com">pengdahai</a>
 * @since 2024/6/6
 */
@Slf4j
@Component
public class DemoBeanD implements BeanClassLoaderAware {

    private ClassLoader classLoader;

    public DemoBeanD() {
        log.info("DemoBeanD constructor");
    }

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;
        log.info("DemoBeanD setBeanClassLoader, classLoader:{}",classLoader);
    }
}
