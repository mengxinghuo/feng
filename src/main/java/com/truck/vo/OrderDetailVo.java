package com.truck.vo;

import java.math.BigDecimal;
import java.util.List;

public class OrderDetailVo {

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

    private String createTime;

    private String updateTime;

    private ShippingVo shippingVo;

    private List<WarehouseVo> warehouseVoList;

    private List<ContactVo> contactVoList;

    private Integer orderDetailStatus;
    private String statusDesc;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public ShippingVo getShippingVo() {
        return shippingVo;
    }

    public void setShippingVo(ShippingVo shippingVo) {
        this.shippingVo = shippingVo;
    }

    public String getStatusDesc() {
        return statusDesc;
    }

    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc;
    }

    public Integer getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(Integer orderDetailId) {
        this.orderDetailId = orderDetailId;
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
        this.productNo = productNo;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
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
        this.productImage = productImage;
    }

    public BigDecimal getCurrentUnitPrice() {
        return currentUnitPrice;
    }

    public void setCurrentUnitPrice(BigDecimal currentUnitPrice) {
        this.currentUnitPrice = currentUnitPrice;
    }


    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }


    public List<WarehouseVo> getWarehouseVoList() {
        return warehouseVoList;
    }

    public void setWarehouseVoList(List<WarehouseVo> warehouseVoList) {
        this.warehouseVoList = warehouseVoList;
    }

    public List<ContactVo> getContactVoList() {
        return contactVoList;
    }

    public void setContactVoList(List<ContactVo> contactVoList) {
        this.contactVoList = contactVoList;
    }

    public Integer getOrderDetailStatus() {
        return orderDetailStatus;
    }

    public void setOrderDetailStatus(Integer orderDetailStatus) {
        this.orderDetailStatus = orderDetailStatus;
    }
}
