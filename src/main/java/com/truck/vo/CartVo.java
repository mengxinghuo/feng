package com.truck.vo;

import java.math.BigDecimal;
import java.util.List;

public class CartVo {
    //private List<CartProductVo> cartProductVoList;

    private List<CartShopListVo> cartShopListVoList;
    private BigDecimal cartTotalprice;
    private Boolean allChecked;//是否全部勾选
    private String imageHost;

    public List<CartShopListVo> getCartShopListVoList() {
        return cartShopListVoList;
    }

    public void setCartShopListVoList(List<CartShopListVo> cartShopListVoList) {
        this.cartShopListVoList = cartShopListVoList;
    }

    public BigDecimal getCartTotalprice() {
        return cartTotalprice;
    }

    public void setCartTotalprice(BigDecimal cartTotalprice) {
        this.cartTotalprice = cartTotalprice;
    }

    public Boolean getAllChecked() {
        return allChecked;
    }

    public void setAllChecked(Boolean allChecked) {
        this.allChecked = allChecked;
    }

    public String getImageHost() {
        return imageHost;
    }

    public void setImageHost(String imageHost) {
        this.imageHost = imageHost;
    }


}
