package com.truck.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class UserInfo {
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

    private Date createTime;

    private Date updateTime;

    public UserInfo(Integer customerId, Integer userId, String companyName, String dutyId, BigDecimal cumulativePurchase, String registerAddress, String officeAddress, String companyPhone, String companyEmail, String companyFax, String companyContactor, String memberLevel, BigDecimal accountBalance, BigDecimal serviceBalance, Date createTime, Date updateTime) {
        this.customerId = customerId;
        this.userId = userId;
        this.companyName = companyName;
        this.dutyId = dutyId;
        this.cumulativePurchase = cumulativePurchase;
        this.registerAddress = registerAddress;
        this.officeAddress = officeAddress;
        this.companyPhone = companyPhone;
        this.companyEmail = companyEmail;
        this.companyFax = companyFax;
        this.companyContactor = companyContactor;
        this.memberLevel = memberLevel;
        this.accountBalance = accountBalance;
        this.serviceBalance = serviceBalance;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public UserInfo() {
        super();
    }

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
        this.companyName = companyName == null ? null : companyName.trim();
    }

    public String getDutyId() {
        return dutyId;
    }

    public void setDutyId(String dutyId) {
        this.dutyId = dutyId == null ? null : dutyId.trim();
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
        this.registerAddress = registerAddress == null ? null : registerAddress.trim();
    }

    public String getOfficeAddress() {
        return officeAddress;
    }

    public void setOfficeAddress(String officeAddress) {
        this.officeAddress = officeAddress == null ? null : officeAddress.trim();
    }

    public String getCompanyPhone() {
        return companyPhone;
    }

    public void setCompanyPhone(String companyPhone) {
        this.companyPhone = companyPhone == null ? null : companyPhone.trim();
    }

    public String getCompanyEmail() {
        return companyEmail;
    }

    public void setCompanyEmail(String companyEmail) {
        this.companyEmail = companyEmail == null ? null : companyEmail.trim();
    }

    public String getCompanyFax() {
        return companyFax;
    }

    public void setCompanyFax(String companyFax) {
        this.companyFax = companyFax == null ? null : companyFax.trim();
    }

    public String getCompanyContactor() {
        return companyContactor;
    }

    public void setCompanyContactor(String companyContactor) {
        this.companyContactor = companyContactor == null ? null : companyContactor.trim();
    }

    public String getMemberLevel() {
        return memberLevel;
    }

    public void setMemberLevel(String memberLevel) {
        this.memberLevel = memberLevel == null ? null : memberLevel.trim();
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