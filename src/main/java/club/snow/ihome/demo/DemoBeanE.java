package club.snow.ihome.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.stereotype.Component;

/**
 * The type DemoBeanE.
 *
 * @author <a href="mailto:pengdahai216@126.com">pengdahai</a>
 * @since 2024/6/6
 */
@Slf4j
@Component
public class DemoBeanE implements DisposableBean {

    public DemoBeanE() {
        log.info("DemoBeanE bean construct");
    }

    @Override
    public void destroy() throws Exception {
        log.info("DemoBeanE bean destroy!");
    }
}
