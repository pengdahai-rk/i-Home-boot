package club.snow.ihome.core.security;

import club.snow.ihome.common.utils.ServletUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import java.io.IOException;

/**
 * The type Customize access denied handler.
 *
 * @author <a href="mailto:pengdahai216@126.com">pengdahai</a>
 * @date 2024.06.25
 */
@Slf4j
public class CustomizeAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        log.info("authentication denied handler error:", accessDeniedException);
        String localizedMessage = accessDeniedException.getLocalizedMessage();
        ServletUtil.writeErrMsg(response, HttpStatus.FORBIDDEN, localizedMessage);
    }
}
