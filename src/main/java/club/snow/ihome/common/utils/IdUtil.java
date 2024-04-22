package club.snow.ihome.common.utils;

import java.util.UUID;

/**
 * The type IdUtil.
 *
 * @author <a href="mailto:pengdahai216@126.com">pengdahai</a>
 * @since 2024/4/18
 */
public class IdUtil {

    /**
     * Random uuid string.
     *
     * @return the string
     */
    public static String randomUUID() {

        return UUID.randomUUID().toString();
    }

}
