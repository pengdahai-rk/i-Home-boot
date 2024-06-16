package club.snow.ihome.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.stereotype.Component;

/**
 * The type DemoBeanC.
 *
 * @author <a href="mailto:pengdahai216@126.com">pengdahai</a>
 * @date 2024.6.6
 */
@Slf4j
@Component
public class DemoBeanC implements BeanNameAware {

    private String beanName;

    public DemoBeanC() {
        log.info("DemoBeanC constructor");
    }

    @Override
    public void setBeanName(String name) {
        beanName = name;
        log.info("DemoBeanC setBeanName, beanName:{}",beanName);
    }
}
