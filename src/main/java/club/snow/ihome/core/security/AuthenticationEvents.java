package club.snow.ihome.core.security;

import club.snow.ihome.bean.dto.UserLoginDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AbstractAuthenticationFailureEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * The type Authentication events.
 *
 * @author <a href="mailto:pengdahai216@126.com">pengdahai</a>
 * @date 2024.06.25
 */
@Slf4j
@Component
public class AuthenticationEvents {

    @EventListener
    public void onSuccess(AuthenticationSuccessEvent successEvent) {
        UserLoginDTO loginDTO = (UserLoginDTO) successEvent.getAuthentication().getPrincipal();
        log.info("user {} authentication success.", loginDTO.getUsername());
    }

    @EventListener
    public void onFailure(AbstractAuthenticationFailureEvent failureEvent) {
        Authentication authentication = failureEvent.getAuthentication();
        if (!Objects.isNull(authentication) && !Objects.isNull(authentication.getPrincipal())) {
            UserLoginDTO loginDTO = (UserLoginDTO) failureEvent.getAuthentication().getPrincipal();
            log.info("user {} authentication failure.", loginDTO.getUsername());
        }
        log.info("authentication failure cause:", failureEvent.getException());
    }

}
