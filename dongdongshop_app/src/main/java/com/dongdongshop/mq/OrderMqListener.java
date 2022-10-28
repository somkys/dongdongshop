package com.dongdongshop.mq;

import com.dongdongshop.entity.Order;
import com.dongdongshop.service.IOrderItemService;
import com.dongdongshop.service.IOrderService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.messaging.Message;
import java.util.Objects;
import java.util.UUID;
import static com.dongdongshop.constants.RedisConstants.SCORE_STATUS_KEY;

@Slf4j
@RocketMQTransactionListener
public class OrderMqListener implements RocketMQLocalTransactionListener {

    @DubboReference
    IOrderService iOrderService;

    @DubboReference
    IOrderItemService iOrderItemService;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    /**
     * 执行本地事务的方法
     * @param message
     * @param o
     * @return
     */
    @Override
    public RocketMQLocalTransactionState executeLocalTransaction(Message message, Object o) {

        Order order = (Order) o;
        UUID id = message.getHeaders().getId();
        try {
            //执行本地事务
            iOrderService.updateLiushuiById(order.getTradeNum(),Integer.parseInt(order.getStatus()),order.getTradeLiushui());
            //添加item表流水号,以订单号为条件
            iOrderItemService.updateLiushuiById(order.getTradeNum(),Integer.parseInt(order.getStatus()),order.getTradeLiushui());
            stringRedisTemplate.opsForValue().set(SCORE_STATUS_KEY+id,"1");
            log.info("本地事务执行成功,回调状态为COMMIT");
            //dbe57bdf-812d-df19-6c43-dd8e10acf197
            return RocketMQLocalTransactionState.COMMIT;
        } catch (NumberFormatException e) {
            e.printStackTrace();
            log.info("本地事务执行失败,回调状态为ROLLBACK");
            stringRedisTemplate.opsForValue().set(SCORE_STATUS_KEY+id,"0");
            return RocketMQLocalTransactionState.ROLLBACK;
        }

    }

    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(Message message) {
        UUID id = message.getHeaders().getId();
        String s = stringRedisTemplate.opsForValue().get(SCORE_STATUS_KEY + id);
        if (Objects.equals(s,"1")){
            return RocketMQLocalTransactionState.COMMIT;
        }

        if (Objects.equals(s,"0")){
            return RocketMQLocalTransactionState.ROLLBACK;
        }

        return RocketMQLocalTransactionState.UNKNOWN;
    }
}
