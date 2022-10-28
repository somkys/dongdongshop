package com.dongdongshop.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dongdongshop.entity.SeckillGoods;
import com.dongdongshop.entity.User;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Smoky
 * @since 2022-10-27
 */
public interface ISeckillGoodsService extends IService<SeckillGoods> {

    List<SeckillGoods> listSeckill();

    SeckillGoods getOneSeckillGoods(Long id);

    String buyseckillGoods(User user, Long id);

}
