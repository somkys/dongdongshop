package com.dongdongshop.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dongdongshop.entity.Order;
import com.dongdongshop.vo.ItemVO;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Smoky
 * @since 2022-10-24
 */
public interface IOrderService extends IService<Order> {

    Map<String,String> insertOrder(Order order, List<String> cartList);

    void updateLiushuiById(String out_trade_no,Integer i, String trade_no);

    List<ItemVO> queryPay(String widtQout_trade_no, String widtQtrade_no);

    Order selectUserIdByOutTradeNo(String out_trade_no);

}
