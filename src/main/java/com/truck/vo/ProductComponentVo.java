package com.truck.vo;


import java.util.List;

public class ProductComponentVo {
    private Integer componentId;

    private Integer productId;

    private String name;

    private List<ProductConfVo> productConfVoList;

    public List<ProductConfVo> getProductConfVoList() {
        return productConfVoList;
    }

    public void setProductConfVoList(List<ProductConfVo> productConfVoList) {
        this.productConfVoList = productConfVoList;
    }

    private String createTime;

    private String updateTime;


    public ProductComponentVo(Integer componentId, Integer productId, String name,List<ProductConfVo> productConfVoList, String createTime, String updateTime) {
        this.componentId = componentId;
        this.productId = productId;
        this.name = name;
        this.productConfVoList = productConfVoList;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public ProductComponentVo() {
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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime == null ? null : updateTime.trim();
    }
}
