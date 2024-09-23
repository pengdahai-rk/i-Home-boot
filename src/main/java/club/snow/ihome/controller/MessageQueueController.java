package club.snow.ihome.controller;

import club.snow.ihome.bean.BaseResult;
import club.snow.ihome.common.mq.producer.MessageProducer;
import club.snow.ihome.common.mq.producer.NormalMessageProducerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Message queue controller.
 *
 * @author <a href="mailto:pengdahai216@126.com">pengdahai</a>
 * @date 2024.09.22
 */
@RestController
@RequestMapping("/api/rocketmq")
public class MessageQueueController {

    @Autowired
    private MessageProducer messageProducer;

    @PostMapping("/normal-message")
    public BaseResult<Boolean> normalMessage(@RequestBody NormalMessageProducerDTO messageProducerDTO) {

        messageProducer.sendMessage(messageProducerDTO);
        return BaseResult.ok(Boolean.TRUE);
    }

}
