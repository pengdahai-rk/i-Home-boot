package club.snow.ihome.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * The type DemoBeanB.
 *
 * @author <a href="mailto:pengdahai216@126.com">pengdahai</a>
 * @since 2024/6/6
 */
@Slf4j
@Component
public class DemoBeanB implements InitializingBean {

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("DemoBeanB.afterPropertiesSet");
    }
}
