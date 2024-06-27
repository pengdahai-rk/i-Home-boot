package club.snow.ihome.bean.domain.entity;

import club.snow.ihome.bean.domain.IHomeBaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.util.Date;

/**
 * The type User info do.
 *
 * @author <a href="mailto:pengdahai216@126.com">pengdahai</a>
 * @date 2024.4.20
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class UserInfoDO extends IHomeBaseDO {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 头像地址
     */
    private String avatar;

    /**
     * 性别 0保密 1男 2女 3其它
     */
    private Integer gender;

    /**
     * 生日
     */
    private Date birthday;

    /**
     * 家乡
     */
    private String hometown;

    /**
     * 关于我
     */
    private String aboutMe;
}