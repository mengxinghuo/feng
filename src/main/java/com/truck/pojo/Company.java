package com.truck.pojo;

import java.util.Date;

public class Company {
    private Integer companyId;

    private Integer userId;

    private String companyName;

    private String companyDesc;

    private String companyEmail;

    private String companyPhone;

    private String companyAddress;

    private String companyHeadimg;

    private String companyFirstimg;

    private String companySubimg;

    private Date createTime;

    private Date updateTime;

    private String companyAccount;

    private String companyTaxCard;

    private String companySppkp;

    private String companyLicence;

    public Company(Integer companyId, Integer userId, String companyName, String companyDesc, String companyEmail, String companyPhone, String companyAddress, String companyHeadimg, String companyFirstimg, String companySubimg,Date createTime, Date updateTime, String companyAccount, String companyTaxCard, String companySppkp, String companyLicence) {
        this.companyId = companyId;
        this.userId = userId;
        this.companyName = companyName;
        this.companyDesc = companyDesc;
        this.companyEmail = companyEmail;
        this.companyPhone = companyPhone;
        this.companyAddress = companyAddress;
        this.companyHeadimg = companyHeadimg;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.companyFirstimg = companyFirstimg;
        this.companySubimg = companySubimg;
        this.companyAccount = companyAccount;
        this.companyTaxCard = companyTaxCard;
        this.companySppkp = companySppkp;
        this.companyLicence = companyLicence;
    }

    public Company() {
        super();
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
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

    public String getCompanyDesc() {
        return companyDesc;
    }

    public void setCompanyDesc(String companyDesc) {
        this.companyDesc = companyDesc;
    }

    public String getCompanyEmail() {
        return companyEmail;
    }

    public void setCompanyEmail(String companyEmail) {
        this.companyEmail = companyEmail;
    }

    public String getCompanyPhone() {
        return companyPhone;
    }

    public void setCompanyPhone(String companyPhone) {
        this.companyPhone = companyPhone;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public String getCompanyHeadimg() {
        return companyHeadimg;
    }

    public void setCompanyHeadimg(String companyHeadimg) {
        this.companyHeadimg = companyHeadimg;
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

    public String getCompanyFirstimg() {
        return companyFirstimg;
    }

    public void setCompanyFirstimg(String companyFirstimg) {
        this.companyFirstimg = companyFirstimg;
    }

    public String getCompanySubimg() {
        return companySubimg;
    }

    public void setCompanySubimg(String companySubimg) {
        this.companySubimg = companySubimg;
    }

    public String getCompanyAccount() {
        return companyAccount;
    }

    public void setCompanyAccount(String companyAccount) {
        this.companyAccount = companyAccount;
    }

    public String getCompanyTaxCard() {
        return companyTaxCard;
    }

    public void setCompanyTaxCard(String companyTaxCard) {
        this.companyTaxCard = companyTaxCard;
    }

    public String getCompanySppkp() {
        return companySppkp;
    }

    public void setCompanySppkp(String companySppkp) {
        this.companySppkp = companySppkp;
    }

    public String getCompanyLicence() {
        return companyLicence;
    }

    public void setCompanyLicence(String companyLicence) {
        this.companyLicence = companyLicence;
    }
}