package com.truck.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class Stock {
    private Integer stockId;

    private Integer productId;

    private Integer adminId;

    private Integer warehouseId;

    private Integer shopId;

    private Integer stockNum;

    private Integer stockLimitNum;

    private Integer stockStatus;

    private Date createTime;

    private Date updateTime;

    private Integer stockCategoryId;

    private BigDecimal inStockPrice;

    private Integer type;

    public Stock(Integer stockId, Integer productId, Integer adminId, Integer warehouseId, Integer shopId, Integer stockNum, Integer stockLimitNum, Integer stockStatus, Date createTime, Date updateTime, Integer stockCategoryId, BigDecimal inStockPrice,Integer type) {
        this.stockId = stockId;
        this.productId = productId;
        this.adminId = adminId;
        this.warehouseId = warehouseId;
        this.shopId = shopId;
        this.stockNum = stockNum;
        this.stockLimitNum = stockLimitNum;
        this.stockStatus = stockStatus;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.stockCategoryId = stockCategoryId;
        this.inStockPrice = inStockPrice;
        this.type = type;
    }

    public Stock() {
        super();
    }

    public Integer getStockId() {
        return stockId;
    }

    public void setStockId(Integer stockId) {
        this.stockId = stockId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public Integer getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(Integer warehouseId) {
        this.warehouseId = warehouseId;
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public Integer getStockNum() {
        return stockNum;
    }

    public void setStockNum(Integer stockNum) {
        this.stockNum = stockNum;
    }

    public Integer getStockLimitNum() {
        return stockLimitNum;
    }

    public void setStockLimitNum(Integer stockLimitNum) {
        this.stockLimitNum = stockLimitNum;
    }

    public Integer getStockStatus() {
        return stockStatus;
    }

    public void setStockStatus(Integer stockStatus) {
        this.stockStatus = stockStatus;
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

    public Integer getStockCategoryId() {
        return stockCategoryId;
    }

    public void setStockCategoryId(Integer stockCategoryId) {
        this.stockCategoryId = stockCategoryId;
    }

    public BigDecimal getInStockPrice() {
        return inStockPrice;
    }

    public void setInStockPrice(BigDecimal inStockPrice) {
        this.inStockPrice = inStockPrice;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}