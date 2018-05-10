package com.truck.vo;

import java.math.BigDecimal;

public class BalanceAlterationVo {

    private Integer balanceAlterationId;

    private Integer userInfoId;

    private String companyName;

    private BigDecimal alterationMoney;

    private Integer alterationStatus;

    private String statusDesc;

    private Integer alterationReason;

    private String reasonDesc;

    private Integer orderId;

    private String orderNo;

    private String createTime;

    private String updateTime;

    public Integer getBalanceAlterationId() {
        return balanceAlterationId;
    }

    public void setBalanceAlterationId(Integer balanceAlterationId) {
        this.balanceAlterationId = balanceAlterationId;
    }

    public Integer getUserInfoId() {
        return userInfoId;
    }

    public void setUserInfoId(Integer userInfoId) {
        this.userInfoId = userInfoId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public BigDecimal getAlterationMoney() {
        return alterationMoney;
    }

    public void setAlterationMoney(BigDecimal alterationMoney) {
        this.alterationMoney = alterationMoney;
    }

    public Integer getAlterationStatus() {
        return alterationStatus;
    }

    public void setAlterationStatus(Integer alterationStatus) {
        this.alterationStatus = alterationStatus;
    }

    public String getStatusDesc() {
        return statusDesc;
    }

    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc;
    }

    public Integer getAlterationReason() {
        return alterationReason;
    }

    public void setAlterationReason(Integer alterationReason) {
        this.alterationReason = alterationReason;
    }

    public String getReasonDesc() {
        return reasonDesc;
    }

    public void setReasonDesc(String reasonDesc) {
        this.reasonDesc = reasonDesc;
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
        this.orderNo = orderNo;
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
}
