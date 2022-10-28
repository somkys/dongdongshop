package com.dongdongshop.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 
 * </p>
 *
 * @author Smoky
 * @since 2022-10-21
 */
@TableName("tb_order_item")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String sid;

    /**
     * 商品id
     */
    private Long itemId;

    /**
     * SPU_ID
     */
    private Long goodsId;

    /**
     * 订单id
     */
    private Long orderId;

    /**
     * 商品标题
     */
    private String title;

    /**
     * 商品单价
     */
    private BigDecimal price;

    /**
     * 商品购买数量
     */
    private Integer num;

    /**
     * 商品总金额
     */
    private BigDecimal totalFee;

    /**
     * 商品图片地址
     */
    private String picPath;

    private String sellerId;

    private String tradeNum;

    private String tradeLiushui;

    private String status;
}
