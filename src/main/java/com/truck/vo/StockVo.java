package com.truck.vo;

import java.math.BigDecimal;

public class StockVo {
    private Integer stockId;

    private Integer productId;

    private Integer adminId;

    private Integer warehouseId;

    private Integer shopId;

    private Integer stockNum;

    private Integer stockLimitNum;

    private Integer stockStatus;

    private String createTime;

    private String updateTime;

    private Integer stockCategoryId;

    private Integer parentCategoryId;

    private BigDecimal inStockPrice;

    private String stockName;

    private String stockStatusDesc;

    private Integer type;

    private String typeDesc;


    public StockVo() {
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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getStockCategoryId() {
        return stockCategoryId;
    }

    public void setStockCategoryId(Integer stockCategoryId) {
        this.stockCategoryId = stockCategoryId;
    }

    public Integer getParentCategoryId() {
        return parentCategoryId;
    }

    public void setParentCategoryId(Integer parentCategoryId) {
        this.parentCategoryId = parentCategoryId;
    }

    public BigDecimal getInStockPrice() {
        return inStockPrice;
    }

    public void setInStockPrice(BigDecimal inStockPrice) {
        this.inStockPrice = inStockPrice;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public String getStockStatusDesc() {
        return stockStatusDesc;
    }

    public void setStockStatusDesc(String stockStatusDesc) {
        this.stockStatusDesc = stockStatusDesc;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getTypeDesc() {
        return typeDesc;
    }

    public void setTypeDesc(String typeDesc) {
        this.typeDesc = typeDesc;
    }
}