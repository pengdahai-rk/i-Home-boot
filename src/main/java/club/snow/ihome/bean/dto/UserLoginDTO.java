package club.snow.ihome.bean.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * The type LoginUser.
 *
 * @author <a href="mailto:pengdahai216@126.com">pengdahai</a>
 * @since 2024/4/21
 */
@Data
public class UserLoginDTO implements Serializable {

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
     * 用户名
     */
    private String userName;

    /**
     * 最近登录时间
     */
    private Long signInDate;

    /**
     * 最后登录IP
     */
    private String signInIp;

    /**
     * 过期时间
     */
    private Long expireTime;

}
