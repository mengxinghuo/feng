package com.truck.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class BalanceAlteration {
    private Integer balanceAlterationId;

    private Integer userInfoId;

    private BigDecimal alterationMoney;

    private Integer alterationStatus;

    private Integer alterationReason;

    private Integer orderId;

    private Date createTime;

    private Date updateTime;

    public BalanceAlteration(Integer balanceAlterationId, Integer userInfoId, BigDecimal alterationMoney, Integer alterationStatus, Integer alterationReason, Integer orderId, Date createTime, Date updateTime) {
        this.balanceAlterationId = balanceAlterationId;
        this.userInfoId = userInfoId;
        this.alterationMoney = alterationMoney;
        this.alterationStatus = alterationStatus;
        this.alterationReason = alterationReason;
        this.orderId = orderId;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public BalanceAlteration() {
        super();
    }

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

    public Integer getAlterationReason() {
        return alterationReason;
    }

    public void setAlterationReason(Integer alterationReason) {
        this.alterationReason = alterationReason;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
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