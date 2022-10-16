package com.dongdongshop.service.impl;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dongdongshop.entity.Goods;
import com.dongdongshop.mapper.GoodsMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dongdongshop.service.IGoodsService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商品表，SPU表 服务实现类
 * </p>
 *
 * @author Smoky
 * @since 2022-10-16
 */
@Service
@DubboService
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements IGoodsService {

}
