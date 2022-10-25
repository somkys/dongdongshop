package com.dongdongshop.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dongdongshop.entity.Order;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Smoky
 * @since 2022-10-24
 */
public interface IOrderService extends IService<Order> {

    void insertOrder(Order order, List<String> cartList);

}
