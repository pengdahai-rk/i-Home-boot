package club.snow.ihome.bean.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * The type BaseDO.
 *
 * @author <a href="mailto:pengdahai216@126.com">pengdahai</a>
 * @since 2024/4/20
 */
@Data
public class IHomeBaseDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    private Long id;

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
