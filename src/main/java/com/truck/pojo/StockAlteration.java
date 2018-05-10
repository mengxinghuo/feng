package com.truck.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class StockAlteration {
    private Integer alterationId;

    private Integer stockId;

    private Integer orderDetailId;

    private Integer alterationNum;

    private Integer alterationStatus;

    private Integer alterationReason;

    private BigDecimal alterationProductPrice;

    private Date createTime;

    private Date updateTime;

    public StockAlteration(Integer alterationId, Integer stockId, Integer orderDetailId, Integer alterationNum, Integer alterationStatus, Integer alterationReason, BigDecimal alterationProductPrice, Date createTime, Date updateTime) {
        this.alterationId = alterationId;
        this.stockId = stockId;
        this.orderDetailId = orderDetailId;
        this.alterationNum = alterationNum;
        this.alterationStatus = alterationStatus;
        this.alterationReason = alterationReason;
        this.alterationProductPrice = alterationProductPrice;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public StockAlteration() {
        super();
    }

    public Integer getAlterationId() {
        return alterationId;
    }

    public void setAlterationId(Integer alterationId) {
        this.alterationId = alterationId;
    }

    public Integer getStockId() {
        return stockId;
    }

    public void setStockId(Integer stockId) {
        this.stockId = stockId;
    }

    public Integer getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(Integer orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    public Integer getAlterationNum() {
        return alterationNum;
    }

    public void setAlterationNum(Integer alterationNum) {
        this.alterationNum = alterationNum;
    }

    public Integer getAlterationStatus() {
        return alterationStatus;
    }

    public void setAlterationStatus(Integer alterationStatus) {
        this.alterationStatus = alterationStatus;
    }

    public Integer getAlterationReason() {
        return alterationReason;
    }

    public void setAlterationReason(Integer alterationReason) {
        this.alterationReason = alterationReason;
    }

    public BigDecimal getAlterationProductPrice() {
        return alterationProductPrice;
    }

    public void setAlterationProductPrice(BigDecimal alterationProductPrice) {
        this.alterationProductPrice = alterationProductPrice;
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