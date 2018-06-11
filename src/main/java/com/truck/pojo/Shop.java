package com.truck.pojo;

import java.util.Date;

public class Shop {
    private Integer shopId;

    private Integer adminId;

    private String shopName;

    private String shopDesc;

    private String shopEmail;

    private String shopPhone;

    private String shopAddress;

    private String shopHeadimg;

    private String shopFirstimg;

    private String shopSubimg;

    private Date createTime;

    private Date updateTime;

    private String shopAccount;

    private String shopTaxCard;

    private String shopSppkp;

    private String shopLicence;

    private String shopProxyCertificate;

    private Integer shopStatus;

    private String postCode;

    public Shop(Integer shopId, Integer adminId, String shopName, String shopDesc, String shopEmail,
                String shopPhone, String shopAddress, String shopHeadimg, String shopFirstimg,
                String shopSubimg, Date createTime, Date updateTime, String shopAccount,
                String shopTaxCard, String shopSppkp, String shopLicence,
                String shopProxyCertificate,Integer shopStatus,String postCode) {
        this.shopId = shopId;
        this.adminId = adminId;
        this.shopName = shopName;
        this.shopDesc = shopDesc;
        this.shopEmail = shopEmail;
        this.shopPhone = shopPhone;
        this.shopAddress = shopAddress;
        this.shopHeadimg = shopHeadimg;
        this.shopFirstimg = shopFirstimg;
        this.shopSubimg = shopSubimg;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.shopAccount = shopAccount;
        this.shopTaxCard = shopTaxCard;
        this.shopSppkp = shopSppkp;
        this.shopLicence = shopLicence;
        this.shopProxyCertificate = shopProxyCertificate;
        this.shopStatus = shopStatus;
        this.postCode = postCode;
    }

    public Shop() {
        super();
    }

    public String getShopFirstimg() {
        return shopFirstimg;
    }

    public void setShopFirstimg(String shopFirstimg) {
        this.shopFirstimg = shopFirstimg == null ? null : shopFirstimg.trim();
    }

    public String getShopSubimg() {
        return shopSubimg;
    }

    public void setShopSubimg(String shopSubimg) {
        this.shopSubimg = shopSubimg == null ? null : shopSubimg.trim();
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName == null ? null : shopName.trim();
    }

    public String getShopDesc() {
        return shopDesc;
    }

    public void setShopDesc(String shopDesc) {
        this.shopDesc = shopDesc == null ? null : shopDesc.trim();
    }

    public String getShopEmail() {
        return shopEmail;
    }

    public void setShopEmail(String shopEmail) {
        this.shopEmail = shopEmail == null ? null : shopEmail.trim();
    }

    public String getShopPhone() {
        return shopPhone;
    }

    public void setShopPhone(String shopPhone) {
        this.shopPhone = shopPhone == null ? null : shopPhone.trim();
    }

    public String getShopAddress() {
        return shopAddress;
    }

    public void setShopAddress(String shopAddress) {
        this.shopAddress = shopAddress == null ? null : shopAddress.trim();
    }

    public String getShopHeadimg() {
        return shopHeadimg;
    }

    public void setShopHeadimg(String shopHeadimg) {
        this.shopHeadimg = shopHeadimg == null ? null : shopHeadimg.trim();
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

    public String getShopAccount() {
        return shopAccount;
    }

    public void setShopAccount(String shopAccount) {
        this.shopAccount = shopAccount;
    }

    public String getShopTaxCard() {
        return shopTaxCard;
    }

    public void setShopTaxCard(String shopTaxCard) {
        this.shopTaxCard = shopTaxCard;
    }

    public String getShopSppkp() {
        return shopSppkp;
    }

    public void setShopSppkp(String shopSppkp) {
        this.shopSppkp = shopSppkp;
    }

    public String getShopLicence() {
        return shopLicence;
    }

    public void setShopLicence(String shopLicence) {
        this.shopLicence = shopLicence;
    }

    public String getShopProxyCertificate() {
        return shopProxyCertificate;
    }

    public void setShopProxyCertificate(String shopProxyCertificate) {
        this.shopProxyCertificate = shopProxyCertificate;
    }

    public Integer getShopStatus() {
        return shopStatus;
    }

    public void setShopStatus(Integer shopStatus) {
        this.shopStatus = shopStatus;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }
}