package club.snow.ihome.service.impl;

import club.snow.ihome.service.IHomeDemoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * The type IHomeDemoServiceImpl.
 *
 * @author <a href="mailto:pengdahai216@126.com">pengdahai</a>
 * @since 2024/4/18
 */
@Slf4j
@Service
public class IHomeDemoServiceImpl implements IHomeDemoService {

    @Override
    public String getString(String words) {
        return "Hello World!" + words;
    }
}
