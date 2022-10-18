package com.dongdongshop.service.impl;

import cn.hutool.json.JSONUtil;
import com.dongdongshop.entity.Goods;
import com.dongdongshop.entity.GoodsDesc;
import com.dongdongshop.entity.Item;
import com.dongdongshop.mapper.GoodsDescMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dongdongshop.service.IGoodsDescService;
import com.dongdongshop.service.IGoodsService;
import com.dongdongshop.service.IItemService;
import com.dongdongshop.vo.GoodsWithGoodsEditVO;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    @DubboReference
    IItemService itemService;

    @Override
    @Transactional
    public boolean addGoodsAndDesc(GoodsWithGoodsEditVO goodsWithGoodsEditVO , String ite) {
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

        baseMapper.insert(goodsDesc);

        List<Item> items = JSONUtil.toList(ite, Item.class);

        List<Item> items1 = items.stream().map(item -> {
            item.setTitle(goodsWithGoodsEditVO.getCaption());
            item.setCategoryId(goodsWithGoodsEditVO.getCategory3Id());
            item.setStatus("1");
            item.setUpdateTime(LocalDateTime.now());
            item.setCreateTime(LocalDateTime.now());
            item.setGoodsId(goods.getId());
            item.setSellerId(goodsWithGoodsEditVO.getSellerId());
            item.setSeller(goodsWithGoodsEditVO.getSeller());
            return item;
        }).collect(Collectors.toList());

        boolean b = itemService.saveBatch(items1);

        if (!b){
            return false;
        }
        return true;
    }
}
