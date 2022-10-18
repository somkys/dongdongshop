package com.dongdongshop.mapper;

import com.dongdongshop.entity.Goods;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dongdongshop.vo.GoodsVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 商品表，SPU表 Mapper 接口
 * </p>
 *
 * @author Smoky
 * @since 2022-10-16
 */
public interface GoodsMapper extends BaseMapper<Goods> {

    List<GoodsVo> listGoodsVo(@Param("goodsName") String goodsName);

    Integer updateAuditStatus(@Param("ids") Long[] ids, @Param("auditStatus") String auditStatus);

}
