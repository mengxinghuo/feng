package com.truck.vo;

import com.truck.pojo.Category;

import java.math.BigDecimal;
import java.util.List;


public class ProductDetailVo {

    private Integer adminId;
    private Integer productId;
    private BigDecimal Long;
    private BigDecimal Wide;
    private BigDecimal High;
    private String productTitle;
    private String productSubtitle;
    private String productPromotion;
    private BigDecimal productWeight;
    private BigDecimal productPrice;
    private Integer productStatus;
    private Integer productStock;
    private String productFirstimg;
    private String createTime;
    private String updateTime;
    private ShopDetailVo shopDetailVo;

    private String imageHost;
    private Integer parentCategoryId;

    private Category category;

    private List imglist;

    private String productDesc;

    private String productSubimg;

    private Integer productCategoryid;

    private String productSize;

    private List<CategoryVo> categoryVoList;

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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public ShopDetailVo getShopDetailVo() {
        return shopDetailVo;
    }

    public void setShopDetailVo(ShopDetailVo shopDetailVo) {
        this.shopDetailVo = shopDetailVo;
    }

    public String getImageHost() {
        return imageHost;
    }

    public void setImageHost(String imageHost) {
        this.imageHost = imageHost;
    }

    public Integer getParentCategoryId() {
        return parentCategoryId;
    }

    public void setParentCategoryId(Integer parentCategoryId) {
        this.parentCategoryId = parentCategoryId;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
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

    public Integer getProductCategoryid() {
        return productCategoryid;
    }

    public void setProductCategoryid(Integer productCategoryid) {
        this.productCategoryid = productCategoryid;
    }

    public String getProductSize() {
        return productSize;
    }

    public void setProductSize(String productSize) {
        this.productSize = productSize;
    }

    public List<CategoryVo> getCategoryVoList() {
        return categoryVoList;
    }

    public void setCategoryVoList(List<CategoryVo> categoryVoList) {
        this.categoryVoList = categoryVoList;
    }
}
