package club.snow.ihome.bean.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serial;
import java.util.Collection;
import java.util.Set;

/**
 * The type LoginUser.
 *
 * @author <a href="mailto:pengdahai216@126.com">pengdahai</a>
 * @date 2024.4.21
 */
@Setter
public class UserLoginDTO implements UserDetails {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 用户名
     */
    private String username;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 密码
     */
    @JsonIgnore
    private transient String password;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 用户唯一标识
     */
    private String token;

    /**
     * 最近登录时间
     */
    private Long signInTime;

    /**
     * 过期时间
     */
    private Long expireTime;

    /**
     * 最近登录IP
     */
    private String signInIp;

    private Set<GrantedAuthority> authorities;

    private boolean accountNonExpired;

    private boolean accountNonLocked;

    private boolean credentialsNonExpired;

    private boolean enabled;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @JsonIgnore
    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    /**
     * 账户是否未过期,过期无法验证
     *
     * @return boolean
     */
    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return this.accountNonExpired;
    }

    /**
     * 指定用户是否解锁,锁定的用户无法进行身份验证
     *
     * @return boolean
     */
    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return this.accountNonLocked;
    }

    /**
     * 指示是否已过期的用户的凭据(密码),过期的凭据防止认证
     *
     * @return boolean
     */
    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return this.credentialsNonExpired;
    }

    /**
     * 是否可用 ,禁用的用户不能身份验证
     *
     * @return boolean
     */
    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public Long getUserId() {
        return userId;
    }

    public String getToken() {
        return token;
    }

    public Long getSignInTime() {
        return signInTime;
    }

    public Long getExpireTime() {
        return expireTime;
    }

    public String getSignInIp() {
        return signInIp;
    }
}
