package com.dongdongshop.service.impl;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dongdongshop.entity.Goods;
import com.dongdongshop.mapper.GoodsMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dongdongshop.page.PageResult;
import com.dongdongshop.service.IGoodsService;
import com.dongdongshop.vo.GoodsVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

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

    @Override
    public  List<GoodsVo> listGoodsVo(String goodsName) {
        List<GoodsVo> goodsVos = baseMapper.listGoodsVo(goodsName);
        return goodsVos;
    }

    @Override
    public boolean updateAuditStatus(Long[] ids,String auditStatus) {
        Integer integer = baseMapper.updateAuditStatus(ids,auditStatus);
        if (integer<=0){
            return false;
        }
        return true;
    }
}
