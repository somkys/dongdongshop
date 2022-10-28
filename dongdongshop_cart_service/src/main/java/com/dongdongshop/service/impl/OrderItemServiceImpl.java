package com.dongdongshop.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dongdongshop.entity.OrderItem;
import com.dongdongshop.mapper.OrderItemMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dongdongshop.service.IOrderItemService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Smoky
 * @since 2022-10-21
 */
@Service
@DubboService
public class OrderItemServiceImpl extends ServiceImpl<OrderItemMapper, OrderItem> implements IOrderItemService {

    @Override
    public List<OrderItem> listByItemId(Long itemId) {
        return baseMapper.selectList(new QueryWrapper<OrderItem>().eq("item_id",itemId));
    }

    @Override
    public void updateOrderIdById(List<Long> orderItemIds,Long orderId,long nextId) {
        baseMapper.updateOrderIdById(orderItemIds,orderId);

        baseMapper.updateTradeNoById(orderItemIds,nextId);
    }

    @Override
    public List<OrderItem> listOrderItem( List<Long> goodsIds) {
        return baseMapper.selectList(new QueryWrapper<OrderItem>().in("goods_id",goodsIds));
    }

    @Override
    public void updateLiushuiById(String out_trade_no,Integer i, String trade_no) {
        baseMapper.updateLiushuiById(out_trade_no,i,trade_no);
    }

    @Override
    public void updateLiushuiByIdd(Long out_biz_no, Integer i,String trade_no) {
        baseMapper.updateLiushuiByIdd(out_biz_no,i,trade_no);
    }

    @Override
    public List<OrderItem> queryPay(String widtQout_trade_no) {
        return baseMapper.selectList(new QueryWrapper<OrderItem>().eq("trade_num",widtQout_trade_no));
    }

}
