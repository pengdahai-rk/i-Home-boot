package club.snow.ihome.bean.req;

import lombok.Data;

/**
 * The type LoginUserReq.
 *
 * @author <a href="mailto:pengdahai216@126.com">pengdahai</a>
 * @date 2024.4.21
 */
@Data
public class SignInReq {
    /**
     * 用户名
     */
    private String userName;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 密码
     */
    private String password;

    /**
     * 登录类别 1-用户名 2-邮箱
     */
    private Integer signInType;
}
