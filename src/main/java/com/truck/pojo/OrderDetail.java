package com.truck.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class OrderDetail {
    private Integer orderDetailId;

    private Integer userId;

    private Integer orderId;

    private Integer productId;

    private String productNo;

    private String productName;

    private Integer quantity;

    private BigDecimal totalPrice;

    private String productImage;

    private BigDecimal currentUnitPrice;

    private Date createTime;

    private Date updateTime;

    private Integer shippingId;

    private Integer orderDetailStatus;

    public OrderDetail(Integer orderDetailId, Integer userId, Integer orderId, Integer productId, String productNo, String productName, Integer quantity, BigDecimal totalPrice, String productImage, BigDecimal currentUnitPrice, Date createTime, Date updateTime, Integer shippingId, Integer orderDetailStatus) {
        this.orderDetailId = orderDetailId;
        this.userId = userId;
        this.orderId = orderId;
        this.productId = productId;
        this.productNo = productNo;
        this.productName = productName;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.productImage = productImage;
        this.currentUnitPrice = currentUnitPrice;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.shippingId = shippingId;
        this.orderDetailStatus = orderDetailStatus;
    }

    public OrderDetail() {
        super();
    }

    public Integer getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(Integer orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductNo() {
        return productNo;
    }

    public void setProductNo(String productNo) {
        this.productNo = productNo == null ? null : productNo.trim();
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName == null ? null : productName.trim();
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage == null ? null : productImage.trim();
    }

    public BigDecimal getCurrentUnitPrice() {
        return currentUnitPrice;
    }

    public void setCurrentUnitPrice(BigDecimal currentUnitPrice) {
        this.currentUnitPrice = currentUnitPrice;
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

    public Integer getShippingId() {
        return shippingId;
    }

    public void setShippingId(Integer shippingId) {
        this.shippingId = shippingId;
    }

    public Integer getOrderDetailStatus() {
        return orderDetailStatus;
    }

    public void setOrderDetailStatus(Integer orderDetailStatus) {
        this.orderDetailStatus = orderDetailStatus;
    }
}