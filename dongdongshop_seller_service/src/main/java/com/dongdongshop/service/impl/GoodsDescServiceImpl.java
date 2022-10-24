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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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

    @Autowired
    GoodsDescMapper goodsDescMapper;

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

        goodsDesc.setSpecificationItems("[{\"attributeName\":\"网络制式\",\"attributeValue\":[\"移动3G\",\"移动4G\",\"联通3G\"]},{\"attributeName\":\"屏幕尺寸\",\"attributeValue\":[\"5.5寸\",\"6寸\"]}]");
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
            item.setSellPoint("标准套装版（内含支架保护套+贴膜），2G RAM，64位真八核，3300mA大电量！");
            item.setCategory("半身裙");
            item.setBrand("联想");
            return item;
        }).collect(Collectors.toList());

        List<Item> itemList = new ArrayList<>();

        for (int i = 0; i < items1.size(); i++) {
            if (i==0){
                items1.get(i).setTitle(items1.get(i).getTitle()+i);
                items1.get(i).setSpec("{\"屏幕尺寸\":\"6寸\",\"网络制式\":\"移动3G\"}");
                itemList.add(items1.get(i));
            }
            if (i==1) {
                items1.get(i).setTitle(items1.get(i).getTitle()+i);
                items1.get(i).setSpec("{\"屏幕尺寸\":\"5.5寸\",\"网络制式\":\"移动3G\"}");
                itemList.add(items1.get(i));
            }
            if (i==2){
                items1.get(i).setTitle(items1.get(i).getTitle()+i);
                items1.get(i).setSpec("{\"屏幕尺寸\":\"6寸\",\"网络制式\":\"移动4G\"}");
                itemList.add(items1.get(i));
            }
            if (i==3){
                items1.get(i).setTitle(items1.get(i).getTitle()+i);
                items1.get(i).setSpec("{\"屏幕尺寸\":\"5.5寸\",\"网络制式\":\"联通3G\"}");
                itemList.add(items1.get(i));
            }

        }

        boolean b = itemService.saveBatch(itemList);

        if (!b){
            return false;
        }
        return true;
    }

    @Override
    public GoodsDesc getGoodsDescById(Long goodsId) {
        return goodsDescMapper.getGoodsDescById(goodsId);
    }
}
