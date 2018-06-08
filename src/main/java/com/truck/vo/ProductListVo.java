package com.truck.vo;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by geely
 */
public class ProductListVo {
    //String时间戳

    private Integer adminId;
    private Integer productId;
    private BigDecimal Long;
    private BigDecimal Wide;
    private BigDecimal High;
    private List<CategoryVo> catagoryVoList;
    private String productTitle;
    private String productSubtitle;
    private String productPromotion;
    private BigDecimal productWeight;
    private BigDecimal productPrice;
    private Integer productStatus;
    private Integer productStock;
    private String productFirstimg;
    private String createtime;
    private String endtime;

    private String idCode;

    private String engineType;

    private String partsNo;

    private String systemNo;

    private String partsSerialNo;

    private String partsNoTwo;

    private String partsNoThree;

    private String partsNoFour;

    private String descEnglish;

    private String unit;

    private String productBrand;

    private Integer stockStatus;

    private Integer picketLine;

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public BigDecimal getLong() {
        return Long;
    }

    public void setLong(BigDecimal aLong) {
        Long = aLong;
    }

    public BigDecimal getWide() {
        return Wide;
    }

    public void setWide(BigDecimal wide) {
        Wide = wide;
    }

    public BigDecimal getHigh() {
        return High;
    }

    public void setHigh(BigDecimal high) {
        High = high;
    }

    public List<CategoryVo> getCatagoryVoList() {
        return catagoryVoList;
    }

    public void setCatagoryVoList(List<CategoryVo> catagoryVoList) {
        this.catagoryVoList = catagoryVoList;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public String getProductSubtitle() {
        return productSubtitle;
    }

    public void setProductSubtitle(String productSubtitle) {
        this.productSubtitle = productSubtitle;
    }

    public String getProductPromotion() {
        return productPromotion;
    }

    public void setProductPromotion(String productPromotion) {
        this.productPromotion = productPromotion;
    }

    public BigDecimal getProductWeight() {
        return productWeight;
    }

    public void setProductWeight(BigDecimal productWeight) {
        this.productWeight = productWeight;
    }


    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    public Integer getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(Integer productStatus) {
        this.productStatus = productStatus;
    }

    public Integer getProductStock() {
        return productStock;
    }

    public void setProductStock(Integer productStock) {
        this.productStock = productStock;
    }

    public String getProductFirstimg() {
        return productFirstimg;
    }

    public void setProductFirstimg(String productFirstimg) {
        this.productFirstimg = productFirstimg;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public String getIdCode() {
        return idCode;
    }

    public void setIdCode(String idCode) {
        this.idCode = idCode;
    }

    public String getEngineType() {
        return engineType;
    }

    public void setEngineType(String engineType) {
        this.engineType = engineType;
    }

    public String getPartsNo() {
        return partsNo;
    }

    public void setPartsNo(String partsNo) {
        this.partsNo = partsNo;
    }

    public String getSystemNo() {
        return systemNo;
    }

    public void setSystemNo(String systemNo) {
        this.systemNo = systemNo;
    }

    public String getPartsSerialNo() {
        return partsSerialNo;
    }

    public void setPartsSerialNo(String partsSerialNo) {
        this.partsSerialNo = partsSerialNo;
    }

    public String getPartsNoTwo() {
        return partsNoTwo;
    }

    public void setPartsNoTwo(String partsNoTwo) {
        this.partsNoTwo = partsNoTwo;
    }

    public String getPartsNoThree() {
        return partsNoThree;
    }

    public void setPartsNoThree(String partsNoThree) {
        this.partsNoThree = partsNoThree;
    }

    public String getPartsNoFour() {
        return partsNoFour;
    }

    public void setPartsNoFour(String partsNoFour) {
        this.partsNoFour = partsNoFour;
    }

    public String getDescEnglish() {
        return descEnglish;
    }

    public void setDescEnglish(String descEnglish) {
        this.descEnglish = descEnglish;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getProductBrand() {
        return productBrand;
    }

    public void setProductBrand(String productBrand) {
        this.productBrand = productBrand;
    }

    public Integer getStockStatus() {
        return stockStatus;
    }

    public void setStockStatus(Integer stockStatus) {
        this.stockStatus = stockStatus;
    }

    public Integer getPicketLine() {
        return picketLine;
    }

    public void setPicketLine(Integer picketLine) {
        this.picketLine = picketLine;
    }
}
