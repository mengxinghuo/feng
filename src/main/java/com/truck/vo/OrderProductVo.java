package com.truck.vo;


import com.truck.pojo.OrderDetail;

import java.math.BigDecimal;
import java.util.List;

public class OrderProductVo {

    private List<OrderDetail> orderDetailList;
    private BigDecimal productTotalPrice;
    private String imageHost;

    public List<OrderDetail> getOrderDetailList() {
        return orderDetailList;
    }

    public void setOrderDetailList(List<OrderDetail> orderDetailList) {
        this.orderDetailList = orderDetailList;
    }

    public BigDecimal getProductTotalPrice() {
        return productTotalPrice;
    }

    public void setProductTotalPrice(BigDecimal productTotalPrice) {
        this.productTotalPrice = productTotalPrice;
    }

    public String getImageHost() {
        return imageHost;
    }

    public void setImageHost(String imageHost) {
        this.imageHost = imageHost;
    }
}
