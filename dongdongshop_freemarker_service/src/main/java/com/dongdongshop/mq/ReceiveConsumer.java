package com.dongdongshop.mq;
import cn.hutool.json.JSONUtil;
import com.dongdongshop.service.FreeService;
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
import org.springframework.stereotype.Component;
import java.util.List;

@Component
@RocketMQMessageListener(topic = "topic1",consumerGroup = "group1")
@Slf4j
public class ReceiveConsumer  implements RocketMQListener<String>, RocketMQPushConsumerLifecycleListener {

    @Autowired
    FreeService freeService;

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
                String body = new String(messageExt.getBody());
                log.info("尝试获取消息内容:{}",body);
                List<Long> longs = JSONUtil.toList(body, Long.class);
                log.info("尝试反序列化消息内容:{}",longs);
                //执行业务逻辑,构造模板对象
                longs.stream().forEach(aLong -> freeService.item(aLong));
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
    }
}
