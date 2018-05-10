package com.truck.pojo;

import java.util.Date;

public class ProductComponent {
    private Integer componentId;

    private Integer productId;

    private String name;

    private Date createTime;

    private Date updateTime;

    public ProductComponent(Integer componentId, Integer productId, String name, Date createTime, Date updateTime) {
        this.componentId = componentId;
        this.productId = productId;
        this.name = name;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public ProductComponent() {
        super();
    }

    public Integer getComponentId() {
        return componentId;
    }

    public void setComponentId(Integer componentId) {
        this.componentId = componentId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
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