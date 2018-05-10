package com.truck.vo;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by geely
 */
public class CartShopListVo {
    private Integer shopId;

    private Integer adminId;

    private String shopName;


    private String shopHeadimg;

    private BigDecimal shopTotalPrice;


    public BigDecimal getShopTotalPrice() {
        return shopTotalPrice;
    }

    public void setShopTotalPrice(BigDecimal shopTotalPrice) {
        this.shopTotalPrice = shopTotalPrice;
    }


    private List<CartProductVo> cartProductVoList;

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

    public String getShopHeadimg() {
        return shopHeadimg;
    }

    public void setShopHeadimg(String shopHeadimg) {
        this.shopHeadimg = shopHeadimg;
    }

    public List<CartProductVo> getCartProductVoList() {
        return cartProductVoList;
    }

    public void setCartProductVoList(List<CartProductVo> cartProductVoList) {
        this.cartProductVoList = cartProductVoList;
    }
}
