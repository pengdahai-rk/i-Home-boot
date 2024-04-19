package club.snow.ihome.bean.domain;

import club.snow.ihome.bean.domain.base.IHomeBaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * The type Home demo do.
 *
 * @author <a href="mailto:pengdahai216@126.com">pengdahai</a>
 * @since 2024 /04/19
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class HomeDemoDO extends IHomeBaseDO {

    private static final long serialVersionUID = 1L;
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
}