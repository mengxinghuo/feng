package com.truck.pojo;

import java.util.Date;

public class Warehouse {
    private Integer warehouseId;

    private Integer shopId;

    private String warehouseName;

    private String warehouseDesc;

    private String warehousePhone;

    private String warehouseEmail;

    private Date createTime;

    private Date updateTime;

    public Warehouse(Integer warehouseId, Integer shopId, String warehouseName, String warehouseDesc, String warehousePhone, String warehouseEmail, Date createTime, Date updateTime) {
        this.warehouseId = warehouseId;
        this.shopId = shopId;
        this.warehouseName = warehouseName;
        this.warehouseDesc = warehouseDesc;
        this.warehousePhone = warehousePhone;
        this.warehouseEmail = warehouseEmail;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Warehouse() {
        super();
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

    public String getWarehouseName() {
        return warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName == null ? null : warehouseName.trim();
    }

    public String getWarehouseDesc() {
        return warehouseDesc;
    }

    public void setWarehouseDesc(String warehouseDesc) {
        this.warehouseDesc = warehouseDesc == null ? null : warehouseDesc.trim();
    }

    public String getWarehousePhone() {
        return warehousePhone;
    }

    public void setWarehousePhone(String warehousePhone) {
        this.warehousePhone = warehousePhone == null ? null : warehousePhone.trim();
    }

    public String getWarehouseEmail() {
        return warehouseEmail;
    }

    public void setWarehouseEmail(String warehouseEmail) {
        this.warehouseEmail = warehouseEmail == null ? null : warehouseEmail.trim();
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