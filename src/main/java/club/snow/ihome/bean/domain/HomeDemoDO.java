package club.snow.ihome.bean.domain;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * The type Home demo do.
 *
 * @author <a href="mailto:pengdahai216@126.com">pengdahai</a>
 * @since 2024 /04/19
 */
@Data
public class HomeDemoDO implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 主键id
     */
    private Long id;

    /**
     * demo名称
     */
    private String demoName;

    /**
     * demo小数
     */
    private BigDecimal demoDecimal;

    /**
     * demo文本
     */
    private String demoText;

    /**
     * demo二进制对象
     */
    private byte[] demoBlob;

    /**
     * demo  status 0正常 1注销 默认0
     */
    private Boolean demoStatus;

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