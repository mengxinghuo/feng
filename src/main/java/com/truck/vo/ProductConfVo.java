package com.truck.vo;


public class ProductConfVo {
    private Integer id;

    private Integer productId;

    private Integer componentId;

    private String paramName;

    private String paramValue;



    public ProductConfVo(Integer id,Integer productId, Integer componentId, String paramName, String paramValue) {
        this.id = id;
        this.paramName = paramName;
        this.paramValue = paramValue;
    }

    public ProductConfVo() {
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
    public String getParamName() {
        return paramName;
    }

    public Integer getComponentId() {
        return componentId;
    }

    public void setComponentId(Integer componentId) {
        this.componentId = componentId;
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


}