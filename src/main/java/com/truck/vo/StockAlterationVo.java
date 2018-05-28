package com.truck.vo;

import java.math.BigDecimal;
import java.util.Date;

public class StockAlterationVo {
    private Integer alterationId;

    private Integer stockId;

    private Integer orderDetailId;

    private Integer alterationNum;

    private Integer alterationStatus;

    private String statusDesc;

    private Integer alterationReason;

    private String reasonDesc;

    private BigDecimal alterationProductPrice;

    private String createTime;

    private String updateTime;

    private StockVo stockVo;

    private String orderNo;

    public StockAlterationVo() {
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

    public String getStatusDesc() {
        return statusDesc;
    }

    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc;
    }

    public String getReasonDesc() {
        return reasonDesc;
    }

    public void setReasonDesc(String reasonDesc) {
        this.reasonDesc = reasonDesc;
    }

    public BigDecimal getAlterationProductPrice() {
        return alterationProductPrice;
    }

    public void setAlterationProductPrice(BigDecimal alterationProductPrice) {
        this.alterationProductPrice = alterationProductPrice;
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

    public StockVo getStockVo() {
        return stockVo;
    }

    public void setStockVo(StockVo stockVo) {
        this.stockVo = stockVo;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }
}