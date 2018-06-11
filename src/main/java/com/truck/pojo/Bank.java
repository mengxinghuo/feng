package com.truck.pojo;

import java.util.Date;

public class Bank {
    private Integer bankId;

    private Integer userId;

    private String bankName;

    private String bankAddress;

    private String bankAccount;

    private String bankUserName;

    private String contactNumber;

    private String presentAddress;

    private Date createTime;

    private Date updateTime;

    private String swiftCode;

    private String currency;

    public Bank(Integer bankId, Integer userId, String bankName, String bankAddress, String bankAccount, String bankUserName, String contactNumber, String presentAddress, Date createTime, Date updateTime,String swiftCode,String currency) {
        this.bankId = bankId;
        this.userId = userId;
        this.bankName = bankName;
        this.bankAddress = bankAddress;
        this.bankAccount = bankAccount;
        this.bankUserName = bankUserName;
        this.contactNumber = contactNumber;
        this.presentAddress = presentAddress;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.swiftCode = swiftCode;
        this.currency = currency;
    }

    public Bank() {
        super();
    }

    public Integer getBankId() {
        return bankId;
    }

    public void setBankId(Integer bankId) {
        this.bankId = bankId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName == null ? null : bankName.trim();
    }

    public String getBankAddress() {
        return bankAddress;
    }

    public void setBankAddress(String bankAddress) {
        this.bankAddress = bankAddress == null ? null : bankAddress.trim();
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount == null ? null : bankAccount.trim();
    }

    public String getBankUserName() {
        return bankUserName;
    }

    public void setBankUserName(String bankUserName) {
        this.bankUserName = bankUserName == null ? null : bankUserName.trim();
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber == null ? null : contactNumber.trim();
    }

    public String getPresentAddress() {
        return presentAddress;
    }

    public void setPresentAddress(String presentAddress) {
        this.presentAddress = presentAddress == null ? null : presentAddress.trim();
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

    public String getSwiftCode() {
        return swiftCode;
    }

    public void setSwiftCode(String swiftCode) {
        this.swiftCode = swiftCode;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}