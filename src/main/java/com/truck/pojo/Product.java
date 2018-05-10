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

    public Product(Integer productId, Integer adminId, Integer productCategoryid, String productTitle, String productSubtitle, String productPromotion, BigDecimal productWeight, String productSize, BigDecimal productPrice, Integer productStatus, Integer productStock, String productFirstimg, Date createtime, Date endtime, String productDesc, String productSubimg) {
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
}