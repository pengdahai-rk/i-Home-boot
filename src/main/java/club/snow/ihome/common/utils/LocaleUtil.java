package club.snow.ihome.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.Locale;

/**
 * The type Locale util.
 *
 * @author <a href="mailto:pengdahai216@126.com">pengdahai</a>
 * @date 2024.06.21
 */
@Slf4j
public class LocaleUtil {

    /**
     * Get string.
     *
     * @param key the key
     * @return the string
     */
    public static String get(String key) {
        return get(key, LocaleContextHolder.getLocale());
    }

    /**
     * Get string.
     *
     * @param key    the key
     * @param params the params
     * @return the string
     */
    public static String get(String key, Object[] params) {
        return get(key, params, LocaleContextHolder.getLocale());
    }

    /**
     * Get string.
     *
     * @param key    the key
     * @param locale the locale
     * @return the string
     */
    public static String get(String key, Locale locale) {
        return get(key, new String[0], locale);
    }

    /**
     * Get string.
     *
     * @param key    the key
     * @param params the params
     * @param locale the locale
     * @return the string
     */
    public static String get(String key, Object[] params, Locale locale) {
        if (StringUtils.isBlank(key)) {
            return "";
        }
        String message;
        try {
            message = getInstance().getMessage(key, params, locale);
        } catch (NoSuchMessageException e) {
            log.warn("not config message, key:{}", key);
            message = key;
        }
        return message;
    }

    private static MessageSource getInstance() {
        return Lazy.messageSource;
    }

    /**
     * 使用懒加载方式实例化messageSource国际化工具
     */
    private static class Lazy {
        private static final MessageSource messageSource = SpringUtil.getBean(MessageSource.class);
    }

}
