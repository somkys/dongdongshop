package com.dongdongshop.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
 * @since 2022-10-24
 */
@TableName("tb_order")
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 订单id
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long orderId;

    /**
     * 实付金额。精确到2位小数;单位:元。如:200.07，表示:200元7分
     */
    private BigDecimal payment;

    /**
     * 支付类型，1、在线支付，2、货到付款
     */
    private String paymentType;

    /**
     * 邮费。精确到2位小数;单位:元。如:200.07，表示:200元7分
     */
    private String postFee;

    /**
     * 状态：1、未付款，2、已付款，3、未发货，4、已发货，5、交易成功，6、交易关闭,7、待评价
     */
    private String status;

    /**
     * 订单创建时间
     */
    private LocalDateTime createTime;

    /**
     * 订单更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 付款时间
     */
    private LocalDateTime paymentTime;

    /**
     * 发货时间
     */
    private LocalDateTime consignTime;

    /**
     * 交易完成时间
     */
    private LocalDateTime endTime;

    /**
     * 交易关闭时间
     */
    private LocalDateTime closeTime;

    /**
     * 物流名称
     */
    private String shippingName;

    /**
     * 物流单号
     */
    private String shippingCode;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 买家留言
     */
    private String buyerMessage;

    /**
     * 买家昵称
     */
    private String buyerNick;

    /**
     * 买家是否已经评价
     */
    private String buyerRate;

    /**
     * 收货人地区名称(省，市，县)街道
     */
    private String receiverAreaName;

    /**
     * 收货人手机
     */
    private String receiverMobile;

    /**
     * 收货人邮编
     */
    private String receiverZipCode;

    /**
     * 收货人
     */
    private String receiver;

    /**
     * 过期时间，定期清理
     */
    private LocalDateTime expire;

    /**
     * 发票类型(普通发票，电子发票，增值税发票)
     */
    private String invoiceType;

    /**
     * 订单来源：1:app端，2：pc端，3：M端，4：微信端，5：手机qq端
     */
    private String sourceType;

    /**
     * 商家ID
     */
    private String sellerId;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
    public BigDecimal getPayment() {
        return payment;
    }

    public void setPayment(BigDecimal payment) {
        this.payment = payment;
    }
    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }
    public String getPostFee() {
        return postFee;
    }

    public void setPostFee(String postFee) {
        this.postFee = postFee;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }
    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }
    public LocalDateTime getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(LocalDateTime paymentTime) {
        this.paymentTime = paymentTime;
    }
    public LocalDateTime getConsignTime() {
        return consignTime;
    }

    public void setConsignTime(LocalDateTime consignTime) {
        this.consignTime = consignTime;
    }
    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }
    public LocalDateTime getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(LocalDateTime closeTime) {
        this.closeTime = closeTime;
    }
    public String getShippingName() {
        return shippingName;
    }

    public void setShippingName(String shippingName) {
        this.shippingName = shippingName;
    }
    public String getShippingCode() {
        return shippingCode;
    }

    public void setShippingCode(String shippingCode) {
        this.shippingCode = shippingCode;
    }
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getBuyerMessage() {
        return buyerMessage;
    }

    public void setBuyerMessage(String buyerMessage) {
        this.buyerMessage = buyerMessage;
    }
    public String getBuyerNick() {
        return buyerNick;
    }

    public void setBuyerNick(String buyerNick) {
        this.buyerNick = buyerNick;
    }
    public String getBuyerRate() {
        return buyerRate;
    }

    public void setBuyerRate(String buyerRate) {
        this.buyerRate = buyerRate;
    }
    public String getReceiverAreaName() {
        return receiverAreaName;
    }

    public void setReceiverAreaName(String receiverAreaName) {
        this.receiverAreaName = receiverAreaName;
    }
    public String getReceiverMobile() {
        return receiverMobile;
    }

    public void setReceiverMobile(String receiverMobile) {
        this.receiverMobile = receiverMobile;
    }
    public String getReceiverZipCode() {
        return receiverZipCode;
    }

    public void setReceiverZipCode(String receiverZipCode) {
        this.receiverZipCode = receiverZipCode;
    }
    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }
    public LocalDateTime getExpire() {
        return expire;
    }

    public void setExpire(LocalDateTime expire) {
        this.expire = expire;
    }
    public String getInvoiceType() {
        return invoiceType;
    }

    public void setInvoiceType(String invoiceType) {
        this.invoiceType = invoiceType;
    }
    public String getSourceType() {
        return sourceType;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }
    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    @Override
    public String toString() {
        return "Order{" +
            "orderId=" + orderId +
            ", payment=" + payment +
            ", paymentType=" + paymentType +
            ", postFee=" + postFee +
            ", status=" + status +
            ", createTime=" + createTime +
            ", updateTime=" + updateTime +
            ", paymentTime=" + paymentTime +
            ", consignTime=" + consignTime +
            ", endTime=" + endTime +
            ", closeTime=" + closeTime +
            ", shippingName=" + shippingName +
            ", shippingCode=" + shippingCode +
            ", userId=" + userId +
            ", buyerMessage=" + buyerMessage +
            ", buyerNick=" + buyerNick +
            ", buyerRate=" + buyerRate +
            ", receiverAreaName=" + receiverAreaName +
            ", receiverMobile=" + receiverMobile +
            ", receiverZipCode=" + receiverZipCode +
            ", receiver=" + receiver +
            ", expire=" + expire +
            ", invoiceType=" + invoiceType +
            ", sourceType=" + sourceType +
            ", sellerId=" + sellerId +
        "}";
    }
}
