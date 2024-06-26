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
    private String userName;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 头像地址
     */
    private String avatar;

    /**
     * 性别 0保密 1男 2女 3其它
     */
    private Integer gender;

    /**
     * 密码
     */
    private String password;
}
