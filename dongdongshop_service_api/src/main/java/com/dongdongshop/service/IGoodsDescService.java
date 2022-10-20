package com.dongdongshop.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dongdongshop.entity.GoodsDesc;
import com.dongdongshop.vo.GoodsWithGoodsEditVO;
import com.dongdongshop.vo.TemplateVo;

/**
 * <p>
 * 商品详细表，SPU表 服务类
 * </p>
 *
 * @author Smoky
 * @since 2022-10-16
 */
public interface IGoodsDescService extends IService<GoodsDesc> {

    boolean addGoodsAndDesc(GoodsWithGoodsEditVO goodsWithGoodsEditVO , String item);


    GoodsDesc getGoodsDescById(Long goodsId);

}
