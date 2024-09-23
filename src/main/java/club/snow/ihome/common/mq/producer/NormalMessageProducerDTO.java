package club.snow.ihome.common.mq.producer;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * The type Normal message producer dto.
 *
 * @author <a href="mailto:pengdahai216@126.com">pengdahai</a>
 * @date 2024.09.22
 */
@Data
public class NormalMessageProducerDTO {
    private Long messageId;
    private String messageName;
    private Integer messageType;
    private BigDecimal messagePrice;
    private Date createTime;
}
