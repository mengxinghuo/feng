package com.truck.vo;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by geely
 */
public class OrderShopListVo {
    private Integer shopId;

    private Integer adminId;

    private String shopName;

    private String shopHeadimg;

    private BigDecimal shopTotalPrice;

    private List<OrderDetailVo> orderDetailVoList;

    public BigDecimal getShopTotalPrice() {
        return shopTotalPrice;
    }

    public void setShopTotalPrice(BigDecimal shopTotalPrice) {
        this.shopTotalPrice = shopTotalPrice;
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

    public String getShopHeadimg() {
        return shopHeadimg;
    }

    public void setShopHeadimg(String shopHeadimg) {
        this.shopHeadimg = shopHeadimg;
    }

    public List<OrderDetailVo> getOrderDetailVoList() {
        return orderDetailVoList;
    }

    public void setOrderDetailVoList(List<OrderDetailVo> orderDetailVoList) {
        this.orderDetailVoList = orderDetailVoList;
    }
}
