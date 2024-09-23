package club.snow.ihome.common.mq.producer;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MessageProducer {

    @Autowired
    private RocketMQTemplate mqTemplate;

    public void sendMessage(NormalMessageProducerDTO producerDTO) {
        Message<NormalMessageProducerDTO> message = MessageBuilder.withPayload(producerDTO).build();
        SendResult sendResult = mqTemplate.syncSend("normal-message-test:normal-tag", message);
        log.info("message send result:{}", sendResult);
    }

}
