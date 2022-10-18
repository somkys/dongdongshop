package com.dongdongshop.service.impl;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dongdongshop.entity.Item;
import com.dongdongshop.mapper.ItemMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dongdongshop.service.IItemService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商品表，SKU表 服务实现类
 * </p>
 *
 * @author Smoky
 * @since 2022-10-17
 */
@Service
@DubboService
public class ItemServiceImpl extends ServiceImpl<ItemMapper, Item> implements IItemService {

}
