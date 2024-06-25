package club.snow.ihome.core.security.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * The interface Email user details service.
 *
 * @author <a href="mailto:pengdahai216@126.com">pengdahai</a>
 * @date 2024.06.25
 */
public interface EmailUserDetailsService {

    /**
     * Load user by email user details.
     *
     * @param email the email
     * @return the user details
     * @throws UsernameNotFoundException the username not found exception
     */
    UserDetails loadUserByEmail(String email) throws UsernameNotFoundException;
}
