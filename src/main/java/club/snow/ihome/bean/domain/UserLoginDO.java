package club.snow.ihome.bean.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * The type User login do.
 *
 * @author <a href="mailto:pengdahai216@126.com">pengdahai</a>
 * @since 2024 /04/18
 */
@Data
public class UserLoginDO implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 主键id
     */
    private Long id;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 是否注销 0正常 1注销 默认0
     */
    private Boolean isActive;

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
     * 盐值
     */
    private String salt;

    /**
     * 最近登录时间
     */
    private Date loginDate;

    /**
     * 最后登录IP
     */
    private String loginIp;

    /**
     * 密码最后更新时间
     */
    private Date pwdUpdateDate;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建者
     */
    private String createBy;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 更新者
     */
    private String updateBy;
}