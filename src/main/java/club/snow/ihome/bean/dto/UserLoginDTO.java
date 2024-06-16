package club.snow.ihome.bean.dto;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * The type LoginUser.
 *
 * @author <a href="mailto:pengdahai216@126.com">pengdahai</a>
 * @since 2024/4/21
 */
public class UserLoginDTO extends User {

    private static final long serialVersionUID = 1L;

    /**
     * 用户唯一标识
     */
    private String token;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 最近登录时间
     */
    private Long signInDate;

    /**
     * 最近登录IP
     */
    private String signInIp;

    /**
     * 过期时间
     */
    private Long expireTime;

    public UserLoginDTO(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public UserLoginDTO(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired,
                        boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getSignInDate() {
        return signInDate;
    }

    public void setSignInDate(Long signInDate) {
        this.signInDate = signInDate;
    }

    public String getSignInIp() {
        return signInIp;
    }

    public void setSignInIp(String signInIp) {
        this.signInIp = signInIp;
    }

    public Long getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Long expireTime) {
        this.expireTime = expireTime;
    }
}
