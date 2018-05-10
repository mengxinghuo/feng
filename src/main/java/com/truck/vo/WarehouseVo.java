package com.truck.vo;

import java.util.List;

public class WarehouseVo {

    private Integer warehouseId;

    private Integer shopId;

    private String warehouseName;

    private String warehouseDesc;

    private String warehousePhone;

    private String warehouseEmail;

    private String createTime;

    private String updateTime;

    private List<Integer> stockCategoryIdList;

    private List<StockVo> stockVoList;

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

    public String getWarehouseName() {
        return warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }

    public String getWarehouseDesc() {
        return warehouseDesc;
    }

    public void setWarehouseDesc(String warehouseDesc) {
        this.warehouseDesc = warehouseDesc;
    }

    public String getWarehousePhone() {
        return warehousePhone;
    }

    public void setWarehousePhone(String warehousePhone) {
        this.warehousePhone = warehousePhone;
    }

    public String getWarehouseEmail() {
        return warehouseEmail;
    }

    public void setWarehouseEmail(String warehouseEmail) {
        this.warehouseEmail = warehouseEmail;
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

    public List<StockVo> getStockVoList() {
        return stockVoList;
    }

    public void setStockVoList(List<StockVo> stockVoList) {
        this.stockVoList = stockVoList;
    }

    public List<Integer> getStockCategoryIdList() {
        return stockCategoryIdList;
    }

    public void setStockCategoryIdList(List<Integer> stockCategoryIdList) {
        this.stockCategoryIdList = stockCategoryIdList;
    }
}
