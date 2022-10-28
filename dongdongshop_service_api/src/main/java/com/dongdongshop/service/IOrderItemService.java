package com.dongdongshop.service;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dongdongshop.entity.OrderItem;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Smoky
 * @since 2022-10-21
 */
public interface IOrderItemService extends IService<OrderItem> {

    List<OrderItem> listByItemId(Long itemId);

    void updateOrderIdById(List<Long> orderItemIds,Long orderId,long nextId);

    List<OrderItem> listOrderItem( List<Long> goodsIds);

    void updateLiushuiById(String out_trade_no, Integer i,String trade_no);

    void updateLiushuiByIdd(Long out_biz_no, Integer i,String trade_no);

    List<OrderItem> queryPay(String widtQout_trade_no);

}
