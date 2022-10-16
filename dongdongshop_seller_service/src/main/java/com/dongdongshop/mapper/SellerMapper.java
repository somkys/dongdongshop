package com.dongdongshop.mapper;

import com.dongdongshop.entity.Seller;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 商家表 Mapper 接口
 * </p>
 *
 * @author Smoky
 * @since 2022-10-13
 */
public interface SellerMapper extends BaseMapper<Seller> {

    int updateStatus(@Param("status") String status, @Param("sellerId") String sellerId);

}
