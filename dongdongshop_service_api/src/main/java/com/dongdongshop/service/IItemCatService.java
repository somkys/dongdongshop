package com.dongdongshop.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dongdongshop.entity.ItemCat;

import java.util.List;

/**
 * <p>
 * 商品类目 服务类
 * </p>
 *
 * @author Smoky
 * @since 2022-10-12
 */
public interface IItemCatService extends IService<ItemCat> {

    List<ItemCat> listWithTree(Long ParentId);


    boolean deleteItemCat(Long[] ids);

}
