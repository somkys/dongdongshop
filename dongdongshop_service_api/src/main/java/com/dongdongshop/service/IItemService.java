package com.dongdongshop.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dongdongshop.entity.Item;

import java.util.List;

/**
 * <p>
 * 商品表，SKU表 服务类
 * </p>
 *
 * @author Smoky
 * @since 2022-10-17
 */
public interface IItemService extends IService<Item> {

    List<Item> getItemByGoodsId(Long goodsId);

}
