package com.dongdongshop.service.impl;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dongdongshop.entity.Goods;
import com.dongdongshop.entity.GoodsDesc;
import com.dongdongshop.mapper.GoodsDescMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dongdongshop.mapper.GoodsMapper;
import com.dongdongshop.service.IGoodsDescService;
import com.dongdongshop.service.IGoodsService;
import com.dongdongshop.vo.GoodsWithGoodsEditVO;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 商品详细表，SPU表 服务实现类
 * </p>
 *
 * @author Smoky
 * @since 2022-10-16
 */
@Service
@DubboService
public class GoodsDescServiceImpl extends ServiceImpl<GoodsDescMapper, GoodsDesc> implements IGoodsDescService {

    @DubboReference
    IGoodsService iGoodsService;

    @Override
    @Transactional
    public boolean addGoodsAndDesc(GoodsWithGoodsEditVO goodsWithGoodsEditVO) {
        Goods goods = new Goods();

        BeanUtils.copyProperties(goodsWithGoodsEditVO,goods);

        //0未审核
        goods.setAuditStatus("0");

        //1未删除,0已删除
        goods.setIsDelete("1");

        //获取主键
        iGoodsService.save(goods);

        GoodsDesc goodsDesc = new GoodsDesc();

        BeanUtils.copyProperties(goodsWithGoodsEditVO,goodsDesc);

        //商品ID
        goodsDesc.setGoodsId(goods.getId());

        int insert = baseMapper.insert(goodsDesc);

        if (insert<=0){
            return false;
        }
        return true;
    }
}
