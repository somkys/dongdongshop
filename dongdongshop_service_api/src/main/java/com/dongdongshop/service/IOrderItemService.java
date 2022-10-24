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

}
