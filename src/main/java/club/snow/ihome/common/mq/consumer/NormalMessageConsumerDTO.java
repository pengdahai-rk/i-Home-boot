package club.snow.ihome.common.mq.consumer;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class NormalMessageConsumerDTO {
    private Long messageId;
    private String messageName;
    private Integer messageType;
    private BigDecimal messagePrice;
    private Date createTime;
}
