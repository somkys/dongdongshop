package com.dongdongshop.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dongdongshop.entity.ItemCat;
import com.dongdongshop.mapper.ItemCatMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dongdongshop.service.IItemCatService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * <p>
 * 商品类目 服务实现类
 * </p>
 *
 * @author Smoky
 * @since 2022-10-12
 */
@Service
@DubboService
@Slf4j
public class ItemCatServiceImpl extends ServiceImpl<ItemCatMapper, ItemCat> implements IItemCatService {

    @Override
    public List<ItemCat> listWithTree(Long ParentId) {
        return baseMapper.selectList(new QueryWrapper<ItemCat>().eq("parent_id", ParentId));
    }

    @Override
    public boolean deleteItemCat(Long[] ids) {

        //所有id的集合
        List<Long> idsAll = new ArrayList<>();

        //一级分类
        List<Long> oneIds = Arrays.asList(ids);

        oneIds.stream().forEach(id->idsAll.add(id));

        //二级分类
        List<Long> twoIds = baseMapper.selectList(new QueryWrapper<ItemCat>().in("parent_id", oneIds))
                .stream()
                .map(itemCat -> itemCat.getId())
                .collect(Collectors.toList());

        if (twoIds.size()<=0){
            int i = baseMapper.deleteBatchIds(idsAll);
            if (i<=0){
                return false;
            }
            return true;
        }

        twoIds.stream().forEach(aLong -> idsAll.add(aLong));

        //三级分类
        baseMapper.selectList(new QueryWrapper<ItemCat>().in("parent_id", twoIds)).stream().forEach(itemCat -> idsAll.add(itemCat.getId()));

        int i = baseMapper.deleteBatchIds(idsAll);

        if (i<=0){
            return false;
        }
        return true;

    }



}
