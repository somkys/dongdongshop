package com.dongdongshop.mapper;

import com.dongdongshop.entity.Order;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dongdongshop.vo.ItemVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Smoky
 * @since 2022-10-24
 */
public interface OrderMapper extends BaseMapper<Order> {

    void updateLiushuiById(@Param("out_trade_no") String out_trade_no, @Param("i") Integer i, @Param("trade_no") String trade_no);

    List<ItemVO> queryPay(@Param("widtQout_trade_no") String widtQout_trade_no);

    Order selectUserIdByOutTradeNo(String out_trade_no);

}
