package com.truck.pojo;

import com.truck.vo.CategoryVo;
import com.truck.vo.ShopDetailVo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class Product {

    private ShopDetailVo shopDetailVo;

    private Category category;

    private List<CategoryVo> categoryVoList;

    private List imglist;

    private String productDesc;

    private String productSubimg;

    private Integer productId;

    private Integer adminId;

    private Integer productCategoryid;

    private String productTitle;

    private String productSubtitle;

    private String productPromotion;

    private BigDecimal productWeight;

    private String productSize;

    private BigDecimal productPrice;

    private Integer productStatus;

    private Integer productStock;

    private String productFirstimg;

    private Date createtime;

    private Date endtime;

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

    private String stockStatusDesc;

    public Product(Integer productId, Integer adminId, Integer productCategoryid, String productTitle, String productSubtitle, String productPromotion, BigDecimal productWeight, String productSize, BigDecimal productPrice, Integer productStatus, Integer productStock, String productFirstimg, Date createtime, Date endtime, String productDesc, String productSubimg,String idCode,String engineType,String partsNo,String systemNo,String partsSerialNo,String partsNoTwo,String partsNoThree,String partsNoFour,String descEnglish,String unit,String productBrand,Integer stockStatus,Integer picketLine,String stockStatusDesc) {
        this.productId = productId;
        this.adminId = adminId;
        this.productCategoryid = productCategoryid;
        this.productTitle = productTitle;
        this.productSubtitle = productSubtitle;
        this.productPromotion = productPromotion;
        this.productWeight = productWeight;
        this.productSize = productSize;
        this.productPrice = productPrice;
        this.productStatus = productStatus;
        this.productStock = productStock;
        this.productFirstimg = productFirstimg;
        this.createtime = createtime;
        this.endtime = endtime;
        this.productDesc= productDesc;
        this.productSubimg = productSubimg;
        this.idCode = idCode;
        this.engineType = engineType;
        this.partsNo = partsNo;
        this.systemNo = systemNo;
        this.partsSerialNo = partsSerialNo;
        this.partsNoTwo = partsNoTwo;
        this.partsNoThree = partsNoThree;
        this.partsNoFour = partsNoFour;
        this.descEnglish = descEnglish;
        this.unit = unit;
        this.productBrand = productBrand;
        this.stockStatus = stockStatus;
        this.picketLine = picketLine;
        this.stockStatusDesc = stockStatusDesc;
    }

    public Product() {
        super();
    }

    private BigDecimal Long;

    private BigDecimal Wide;

    private BigDecimal High;

    public ShopDetailVo getShopDetailVo() {
        return shopDetailVo;
    }

    public void setShopDetailVo(ShopDetailVo shopDetailVo) {
        this.shopDetailVo = shopDetailVo;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<CategoryVo> getCategoryVoList() {
        return categoryVoList;
    }

    public void setCategoryVoList(List<CategoryVo> categoryVoList) {
        this.categoryVoList = categoryVoList;
    }

    public List getImglist() {
        return imglist;
    }

    public void setImglist(List imglist) {
        this.imglist = imglist;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public String getProductSubimg() {
        return productSubimg;
    }

    public void setProductSubimg(String productSubimg) {
        this.productSubimg = productSubimg;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public Integer getProductCategoryid() {
        return productCategoryid;
    }

    public void setProductCategoryid(Integer productCategoryid) {
        this.productCategoryid = productCategoryid;
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

    public String getProductSize() {
        return productSize;
    }

    public void setProductSize(String productSize) {
        this.productSize = productSize;
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

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
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

    public String getStockStatusDesc() {
        return stockStatusDesc;
    }

    public void setStockStatusDesc(String stockStatusDesc) {
        this.stockStatusDesc = stockStatusDesc;
    }
}