package com.truck.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class Order {
    private Integer orderId;

    private String orderNo;

    private Integer userId;

    private Integer shopId;

    private BigDecimal orderPrice;

    private BigDecimal paymentPrice;

    private Integer paymentType;

    private BigDecimal freight;

    private Integer orderStatus;

    private Date paymentTime;

    private Date createTime;

    private Date updateTime;

    public Order(Integer orderId, String orderNo, Integer userId, Integer shopId, BigDecimal orderPrice, BigDecimal paymentPrice, Integer paymentType, BigDecimal freight, Integer orderStatus, Date paymentTime, Date createTime, Date updateTime) {
        this.orderId = orderId;
        this.orderNo = orderNo;
        this.userId = userId;
        this.shopId = shopId;
        this.orderPrice = orderPrice;
        this.paymentPrice = paymentPrice;
        this.paymentType = paymentType;
        this.freight = freight;
        this.orderStatus = orderStatus;
        this.paymentTime = paymentTime;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Order() {
        super();
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public BigDecimal getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(BigDecimal orderPrice) {
        this.orderPrice = orderPrice;
    }

    public BigDecimal getPaymentPrice() {
        return paymentPrice;
    }

    public void setPaymentPrice(BigDecimal paymentPrice) {
        this.paymentPrice = paymentPrice;
    }

    public Integer getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(Integer paymentType) {
        this.paymentType = paymentType;
    }

    public BigDecimal getFreight() {
        return freight;
    }

    public void setFreight(BigDecimal freight) {
        this.freight = freight;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Date getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(Date paymentTime) {
        this.paymentTime = paymentTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}