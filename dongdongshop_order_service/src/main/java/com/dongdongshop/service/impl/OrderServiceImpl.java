package com.dongdongshop.service.impl;
import cn.hutool.json.JSONUtil;
import com.dongdongshop.entity.Order;
import com.dongdongshop.entity.OrderItem;
import com.dongdongshop.mapper.OrderMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dongdongshop.service.IOrderItemService;
import com.dongdongshop.service.IOrderService;
import com.dongdongshop.vo.ItemVO;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Smoky
 * @since 2022-10-24
 */
@Service
@DubboService
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {

    @DubboReference
    IOrderItemService iOrderItemService;

    @Override
    public void insertOrder(Order order, List<String> cartList) {
        //开始构造数据
        order.setStatus("1");
        order.setUpdateTime(LocalDateTime.now());
        order.setCreateTime(LocalDateTime.now());
        order.setBuyerRate("1");
        order.setInvoiceType("1");
        order.setSourceType("2");
        //获取购物车数据
        List<ItemVO> itemVOS = cartList.stream().map(s -> JSONUtil.toBean(s, ItemVO.class)).collect(Collectors.toList());
        //获取商家id
        ItemVO itemVO = itemVOS.get(0);
        order.setSellerId(itemVO.getSellerID());
        OrderItem orderItem1 = itemVO.getOrderItemList().get(0);
        //总价
        BigDecimal totalFee = orderItem1.getTotalFee();
        order.setPayment(totalFee);

        baseMapper.insert(order);

        List<OrderItem> orderItemList = iOrderItemService.listOrderItem(itemVO.getOrderItemList().get(0).getGoodsId());

        List<Long> OrderItemIds = orderItemList.stream().map(orderItem -> orderItem.getId()).collect(Collectors.toList());

        //保存orderId
        iOrderItemService.updateOrderIdById(OrderItemIds,order.getOrderId());

    }
}
