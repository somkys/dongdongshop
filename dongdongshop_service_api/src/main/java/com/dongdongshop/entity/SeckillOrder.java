package com.dongdongshop.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author Smoky
 * @since 2022-10-27
 */
@TableName("tb_seckill_order")
public class SeckillOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 秒杀商品ID
     */
    private Long seckillId;

    /**
     * 支付金额
     */
    private BigDecimal money;

    /**
     * 用户
     */
    private String userId;

    /**
     * 商家
     */
    private String sellerId;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 支付时间
     */
    private LocalDateTime payTime;

    /**
     * 状态
     */
    private String status;

    /**
     * 收货人地址
     */
    private String receiverAddress;

    /**
     * 收货人电话
     */
    private String receiverMobile;

    /**
     * 收货人
     */
    private String receiver;

    /**
     * 交易流水
     */
    private String transactionId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Long getSeckillId() {
        return seckillId;
    }

    public void setSeckillId(Long seckillId) {
        this.seckillId = seckillId;
    }
    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }
    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }
    public LocalDateTime getPayTime() {
        return payTime;
    }

    public void setPayTime(LocalDateTime payTime) {
        this.payTime = payTime;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public String getReceiverAddress() {
        return receiverAddress;
    }

    public void setReceiverAddress(String receiverAddress) {
        this.receiverAddress = receiverAddress;
    }
    public String getReceiverMobile() {
        return receiverMobile;
    }

    public void setReceiverMobile(String receiverMobile) {
        this.receiverMobile = receiverMobile;
    }
    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }
    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    @Override
    public String toString() {
        return "SeckillOrder{" +
            "id=" + id +
            ", seckillId=" + seckillId +
            ", money=" + money +
            ", userId=" + userId +
            ", sellerId=" + sellerId +
            ", createTime=" + createTime +
            ", payTime=" + payTime +
            ", status=" + status +
            ", receiverAddress=" + receiverAddress +
            ", receiverMobile=" + receiverMobile +
            ", receiver=" + receiver +
            ", transactionId=" + transactionId +
        "}";
    }
}
