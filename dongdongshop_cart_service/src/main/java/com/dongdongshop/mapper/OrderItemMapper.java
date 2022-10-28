package com.dongdongshop.mapper;

import com.dongdongshop.entity.OrderItem;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Smoky
 * @since 2022-10-21
 */
public interface OrderItemMapper extends BaseMapper<OrderItem> {

    void updateOrderIdById(@Param("orderItemIds") List<Long> orderItemIds, @Param("orderId") Long orderId);

    void updateTradeNoById(@Param("orderItemIds") List<Long> orderItemIds, @Param("nextId") long nextId);

    void updateLiushuiById(@Param("out_trade_no") String out_trade_no, @Param("i") Integer i, @Param("trade_no") String trade_no);

    void updateLiushuiByIdd(@Param("out_biz_no") Long out_biz_no, @Param("i") Integer i, @Param("trade_no") String trade_no);

}
