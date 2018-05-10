package com.truck.pojo;

import java.util.Date;

public class CompanyAddress {
    private Integer addressId;

    private Integer companyId;

    private String addressName;

    private String addressDesc;

    private String addressPhone;

    private String addressEmail;

    private Date createTime;

    private Date updateTime;

    public CompanyAddress(Integer addressId, Integer companyId, String addressName, String addressDesc, String addressPhone, String addressEmail, Date createTime, Date updateTime) {
        this.addressId = addressId;
        this.companyId = companyId;
        this.addressName = addressName;
        this.addressDesc = addressDesc;
        this.addressPhone = addressPhone;
        this.addressEmail = addressEmail;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public CompanyAddress() {
        super();
    }

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getAddressName() {
        return addressName;
    }

    public void setAddressName(String addressName) {
        this.addressName = addressName == null ? null : addressName.trim();
    }

    public String getAddressDesc() {
        return addressDesc;
    }

    public void setAddressDesc(String addressDesc) {
        this.addressDesc = addressDesc == null ? null : addressDesc.trim();
    }

    public String getAddressPhone() {
        return addressPhone;
    }

    public void setAddressPhone(String addressPhone) {
        this.addressPhone = addressPhone == null ? null : addressPhone.trim();
    }

    public String getAddressEmail() {
        return addressEmail;
    }

    public void setAddressEmail(String addressEmail) {
        this.addressEmail = addressEmail == null ? null : addressEmail.trim();
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