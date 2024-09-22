package club.snow.ihome.common.mq.consumer;


import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**
 * The type Normal message consumer.
 *
 * @author <a href="mailto:pengdahai216@126.com">pengdahai</a>
 * @date 2024.09.21
 */
@Slf4j
@Component
@RocketMQMessageListener(topic = "${rocketmq.i-home-conf.normal-topic}", consumerGroup = "${rocketmq.consumer.group}", selectorExpression = "normal-tag")
public class NormalMessageConsumer implements RocketMQListener<NormalMessageConsumerDTO> {

    @Override
    public void onMessage(NormalMessageConsumerDTO message) {
        log.info("消费到消息：{}", message);
    }
}
