package com.truck.pojo;

import java.util.Date;

public class ProductConf {
    private Integer id;

    private Integer productId;

    private Integer componentId;

    private String paramName;

    private String paramValue;

    private Date createTime;

    private Date updateTime;

    public ProductConf(Integer id, Integer productId, Integer componentId, String paramName, String paramValue, Date createTime, Date updateTime) {
        this.id = id;
        this.productId = productId;
        this.componentId = componentId;
        this.paramName = paramName;
        this.paramValue = paramValue;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public ProductConf() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getComponentId() {
        return componentId;
    }

    public void setComponentId(Integer componentId) {
        this.componentId = componentId;
    }

    public String getParamName() {
        return paramName;
    }

    public void setParamName(String paramName) {
        this.paramName = paramName == null ? null : paramName.trim();
    }

    public String getParamValue() {
        return paramValue;
    }

    public void setParamValue(String paramValue) {
        this.paramValue = paramValue == null ? null : paramValue.trim();
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