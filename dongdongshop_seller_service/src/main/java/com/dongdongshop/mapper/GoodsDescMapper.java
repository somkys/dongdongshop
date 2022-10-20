package com.dongdongshop.mapper;

import com.dongdongshop.entity.GoodsDesc;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 商品详细表，SPU表 Mapper 接口
 * </p>
 *
 * @author Smoky
 * @since 2022-10-16
 */
@Mapper
public interface GoodsDescMapper extends BaseMapper<GoodsDesc> {

    GoodsDesc getGoodsDescById(@Param("goodsId") Long goodsId);

}
