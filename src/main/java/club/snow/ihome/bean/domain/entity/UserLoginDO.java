package club.snow.ihome.bean.domain.entity;

import club.snow.ihome.bean.domain.IHomeBaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.util.Date;


/**
 * The type User login do.
 *
 * @author <a href="mailto:pengdahai216@126.com">pengdahai</a>
 * @date 2024 /04/18
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class UserLoginDO extends IHomeBaseDO {

    @Serial
    private static final long serialVersionUID = 1L;
    /**
     * 用户名
     */
    private String userName;

    /**
     * 是否注销 0正常 1注销 默认0
     */
    private Integer isActive;

    /**
     * 用户类型（00系统用户 01注册用户）
     */
    private String userType;

    /**
     * 密码
     */
    private String password;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 最近登录时间
     */
    private Date signInDate;

    /**
     * 最后登录IP
     */
    private String signInIp;

    /**
     * 密码最后更新时间
     */
    private Date pwdUpdateDate;
}