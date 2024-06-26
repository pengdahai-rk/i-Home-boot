package club.snow.ihome.bean.domain.entity;

import club.snow.ihome.bean.domain.IHomeBaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.math.BigDecimal;

/**
 * The type Home demo do.
 *
 * @author <a href="mailto:pengdahai216@126.com">pengdahai</a>
 * @date 2024 /04/19
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class HomeDemoDO extends IHomeBaseDO {

    @Serial
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