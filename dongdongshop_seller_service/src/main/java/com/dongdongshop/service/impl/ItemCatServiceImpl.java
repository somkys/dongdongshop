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
import org.springframework.transaction.annotation.Transactional;

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

        //一级分类
        List<Long> oneIds = Arrays.asList(ids);

        //判断当前节点是否还有子节点
        List<ItemCat> itemCats = baseMapper.selectList(new QueryWrapper<ItemCat>().in("parent_id", oneIds));

        //itemCats != null
        if (itemCats.size()>0){
            return false;
        }

        int i = baseMapper.deleteBatchIds(oneIds);

        if (i<=0){
            return false;
        }
        return true;

    }

}
