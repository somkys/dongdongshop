package com.dongdongshop.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dongdongshop.entity.OrderItem;
import com.dongdongshop.mapper.OrderItemMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dongdongshop.service.IOrderItemService;
import org.apache.dubbo.config.annotation.DubboReference;
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
}
