package com.truck.vo;

import java.math.BigDecimal;

public class UserInfoVo {

    private Integer customerId;

    private Integer userId;

    private String companyName;

    private String dutyId;

    private BigDecimal cumulativePurchase;

    private String registerAddress;

    private String officeAddress;

    private String companyPhone;

    private String companyEmail;

    private String companyFax;

    private String companyContactor;

    private String memberLevel;

    private BigDecimal accountBalance;

    private BigDecimal serviceBalance;

    private String createTime;

    private String updateTime;

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getDutyId() {
        return dutyId;
    }

    public void setDutyId(String dutyId) {
        this.dutyId = dutyId;
    }

    public BigDecimal getCumulativePurchase() {
        return cumulativePurchase;
    }

    public void setCumulativePurchase(BigDecimal cumulativePurchase) {
        this.cumulativePurchase = cumulativePurchase;
    }

    public String getRegisterAddress() {
        return registerAddress;
    }

    public void setRegisterAddress(String registerAddress) {
        this.registerAddress = registerAddress;
    }

    public String getOfficeAddress() {
        return officeAddress;
    }

    public void setOfficeAddress(String officeAddress) {
        this.officeAddress = officeAddress;
    }

    public String getCompanyPhone() {
        return companyPhone;
    }

    public void setCompanyPhone(String companyPhone) {
        this.companyPhone = companyPhone;
    }

    public String getCompanyEmail() {
        return companyEmail;
    }

    public void setCompanyEmail(String companyEmail) {
        this.companyEmail = companyEmail;
    }

    public String getCompanyFax() {
        return companyFax;
    }

    public void setCompanyFax(String companyFax) {
        this.companyFax = companyFax;
    }

    public String getCompanyContactor() {
        return companyContactor;
    }

    public void setCompanyContactor(String companyContactor) {
        this.companyContactor = companyContactor;
    }

    public String getMemberLevel() {
        return memberLevel;
    }

    public void setMemberLevel(String memberLevel) {
        this.memberLevel = memberLevel;
    }

    public BigDecimal getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(BigDecimal accountBalance) {
        this.accountBalance = accountBalance;
    }

    public BigDecimal getServiceBalance() {
        return serviceBalance;
    }

    public void setServiceBalance(BigDecimal serviceBalance) {
        this.serviceBalance = serviceBalance;
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
