package com.dongdongshop.service.impl;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dongdongshop.entity.Order;
import com.dongdongshop.entity.OrderItem;
import com.dongdongshop.mapper.OrderMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dongdongshop.service.IOrderItemService;
import com.dongdongshop.service.IOrderService;
import com.dongdongshop.utils.IdWorker;
import com.dongdongshop.vo.ItemVO;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
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
    public Map<String,String> insertOrder(Order order, List<String> cartList) {
        //开始构造数据
        order.setStatus("1");
        order.setUpdateTime(LocalDateTime.now());
        order.setCreateTime(LocalDateTime.now());
        order.setBuyerRate("1");
        order.setInvoiceType("1");
        order.setSourceType("2");
        //获取购物车数据
        List<ItemVO> itemVOS = cartList.stream().map(s -> JSONUtil.toBean(s, ItemVO.class)).collect(Collectors.toList());
        //总价
        BigDecimal sum = new BigDecimal("0");

        //商户订单号
        IdWorker idWorker = new IdWorker(0, 0);
        long nextId = idWorker.nextId();

        //获取商家id
        for (ItemVO itemVO : itemVOS) {
            order.setSellerId(itemVO.getSellerID());

            List<OrderItem> orderItemList1 = itemVO.getOrderItemList();

            for (OrderItem orderItem : orderItemList1) {
                sum = sum.add(orderItem.getTotalFee());
            }

        }

        List<Long> goodsIds = new ArrayList<>();

        for (ItemVO itemVO : itemVOS) {
            List<OrderItem> orderItemList = itemVO.getOrderItemList();
            for (OrderItem orderItem : orderItemList) {
                goodsIds.add(orderItem.getGoodsId());
            }
        }

        order.setPayment(sum);

        order.setTradeNum(String.valueOf(nextId));

        baseMapper.insert(order);

        List<OrderItem> orderItemList = iOrderItemService.listOrderItem(goodsIds);

        List<Long> OrderItemIds = orderItemList.stream().map(orderItem -> orderItem.getId()).collect(Collectors.toList());

        //保存orderId
        iOrderItemService.updateOrderIdById(OrderItemIds, order.getOrderId(),nextId);

        Map map = new HashMap<String,String>();

        map.put("WIDout_trade_no",String.valueOf(nextId));

        map.put("WIDtotal_amount",sum);

        return map;
    }

    @Override
    public void updateLiushuiById(String out_trade_no,Integer i, String trade_no) {
        baseMapper.updateLiushuiById(out_trade_no,i,trade_no);
    }

    @Override
    public List<ItemVO> queryPay(String widtQout_trade_no, String widtQtrade_no) {
        List<ItemVO> itemVOList = baseMapper.queryPay(widtQout_trade_no);
        return itemVOList;
    }

    @Override
    public Order selectUserIdByOutTradeNo(String out_trade_no) {
        return baseMapper.selectUserIdByOutTradeNo(out_trade_no);
    }

}
