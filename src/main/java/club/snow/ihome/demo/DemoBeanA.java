package club.snow.ihome.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * The type DemoBean.
 *
 * @author <a href="mailto:pengdahai216@126.com">pengdahai</a>
 * @date 2024.6.6
 */
@Slf4j
@Component
public class DemoBeanA implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        log.info("DemoBeanA.postProcessBeforeInitialization, beanName:{}",beanName);
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        log.info( "DemoBeanA.postProcessAfterInitialization, beanName:{}",beanName);
        return bean;
    }
}
