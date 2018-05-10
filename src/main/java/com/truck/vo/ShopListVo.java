package com.truck.vo;

import java.util.List;

/**
 * Created by geely
 */
public class ShopListVo {
    private Integer shopId;

    private Integer adminId;

    private String shopName;

    private String shopDesc;

    private String shopHeadimg;

    private String shopFirstimg;

    private List<String> shopSubimg;

    private List<ProductListVo> productListVoList;

    private Integer shopStatus;

    public List<ProductListVo> getProductListVoList() {
        return productListVoList;
    }

    public void setProductListVoList(List<ProductListVo> productListVoList) {
        this.productListVoList = productListVoList;
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
        this.shopName = shopName;
    }

    public String getShopDesc() {
        return shopDesc;
    }

    public void setShopDesc(String shopDesc) {
        this.shopDesc = shopDesc;
    }

    public String getShopHeadimg() {
        return shopHeadimg;
    }

    public void setShopHeadimg(String shopHeadimg) {
        this.shopHeadimg = shopHeadimg;
    }

    public String getShopFirstimg() {
        return shopFirstimg;
    }

    public void setShopFirstimg(String shopFirstimg) {
        this.shopFirstimg = shopFirstimg;
    }

    public List<String> getShopSubimg() {
        return shopSubimg;
    }

    public void setShopSubimg(List<String> shopSubimg) {
        this.shopSubimg = shopSubimg;
    }

    public Integer getShopStatus() {
        return shopStatus;
    }

    public void setShopStatus(Integer shopStatus) {
        this.shopStatus = shopStatus;
    }
}
