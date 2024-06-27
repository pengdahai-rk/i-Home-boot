package club.snow.ihome.bean.req;

import lombok.Data;


/**
 * The type Sign up req.
 *
 * @author <a href="mailto:pengdahai216@126.com">pengdahai</a>
 * @date 2024 /04/23
 */
@Data
public class SignUpReq {

    /**
     * 用户名
     */
    private String username;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 密码
     */
    private String password;
}
