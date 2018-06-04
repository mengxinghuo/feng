package com.truck.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class Cart {
    private Integer cartId;

    private Integer cartUserId;

    private Integer cartProductId;

    private Integer amount;

    private Integer checked;

    private Date createTime;

    private Date updateTime;

    private Integer shopId;

    private BigDecimal cartPrice;

    public Cart(Integer cartId, Integer cartUserId, Integer cartProductId, Integer amount, Integer checked, Date createTime, Date updateTime, Integer shopId,BigDecimal cartPrice) {
        this.cartId = cartId;
        this.cartUserId = cartUserId;
        this.cartProductId = cartProductId;
        this.amount = amount;
        this.checked = checked;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.shopId = shopId;
        this.cartPrice = cartPrice;
    }

    public Cart() {
        super();
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public Integer getCartId() {
        return cartId;
    }

    public void setCartId(Integer cartId) {
        this.cartId = cartId;
    }

    public Integer getCartUserId() {
        return cartUserId;
    }

    public void setCartUserId(Integer cartUserId) {
        this.cartUserId = cartUserId;
    }

    public Integer getCartProductId() {
        return cartProductId;
    }

    public void setCartProductId(Integer cartProductId) {
        this.cartProductId = cartProductId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getChecked() {
        return checked;
    }

    public void setChecked(Integer checked) {
        this.checked = checked;
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

    public BigDecimal getCartPrice() {
        return cartPrice;
    }

    public void setCartPrice(BigDecimal cartPrice) {
        this.cartPrice = cartPrice;
    }
}