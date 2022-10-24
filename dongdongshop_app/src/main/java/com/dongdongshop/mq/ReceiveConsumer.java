package com.dongdongshop.mq;
import com.dongdongshop.service.RegisterService;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.apache.rocketmq.spring.core.RocketMQPushConsumerLifecycleListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.concurrent.TimeUnit;
import static com.dongdongshop.constants.RedisConstants.REGISTER_CODE_KEY;
import static com.dongdongshop.constants.RedisConstants.REGISTER_CODE_TTL;


@Component
@RocketMQMessageListener(topic = "topic2",consumerGroup = "group2")
@Slf4j
public class ReceiveConsumer implements RocketMQListener<String>, RocketMQPushConsumerLifecycleListener {

    @Autowired
    RegisterService registerService;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Override
    public void onMessage(String s) {

    }

    @Override
    public void prepareStart(DefaultMQPushConsumer defaultMQPushConsumer) {
        defaultMQPushConsumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
                MessageExt messageExt = list.get(0);
                //尝试获取消息体内容
                String phone = new String(messageExt.getBody());
                log.info("尝试获取消息内容:{}",phone);
                //执行业务逻辑
                String messageCode = registerService.getMessageCode(phone);
                //发送成功后加入redis,设置超时时间为30分钟
                stringRedisTemplate.opsForValue().set(REGISTER_CODE_KEY+phone,messageCode,REGISTER_CODE_TTL, TimeUnit.MINUTES);
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
    }
}
