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

}
