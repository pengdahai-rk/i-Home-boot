package club.snow.ihome.common.i18n;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AbstractLocaleResolver;

import java.util.Locale;
import java.util.Objects;

/**
 * The type Home locale resolver.
 *
 * @author <a href="mailto:pengdahai216@126.com">pengdahai</a>
 * @date 2024.06.20
 */
@Component
public class IHomeLocaleResolver extends AbstractLocaleResolver {

    @NonNull
    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        String lang = request.getHeader("lang");
        // 仅支持中英文，默认中文
        if (StringUtils.isNotBlank(lang) && Objects.equals(lang, "en-Us")) {
            return Locale.US;
        }
        return Locale.getDefault();
    }

    @Override
    public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {
        throw new UnsupportedOperationException(
                "Cannot change HTTP Accept-Language header - use a different locale resolution strategy");
    }

    @Bean
    public LocaleResolver localeResolver() {
        IHomeLocaleResolver iHomeLocaleResolver = new IHomeLocaleResolver();
        iHomeLocaleResolver.setDefaultLocale(Locale.CHINA);
        return iHomeLocaleResolver;
    }
}
