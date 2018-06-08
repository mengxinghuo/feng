package com.truck.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.truck.common.Const;
import com.truck.common.ResponseCode;
import com.truck.common.ServerResponse;
import com.truck.dao.CartMapper;
import com.truck.dao.CategoryMapper;
import com.truck.dao.ProductMapper;
import com.truck.dao.ShopMapper;
import com.truck.pojo.Cart;
import com.truck.pojo.Category;
import com.truck.pojo.Product;
import com.truck.pojo.Shop;
import com.truck.service.CategoryService;
import com.truck.service.ProductService;
import com.truck.util.DateTimeUtil;
import com.truck.util.PropertiesUtil;
import com.truck.vo.CategoryVo;
import com.truck.vo.ProductDetailVo;
import com.truck.vo.ProductListVo;
import com.truck.vo.ShopDetailVo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by master on 2018/3/12.
 * <p>
 * 业务逻辑层
 */

@Service("ProductService")
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;
    @Autowired
    CartMapper cartMapper;
    @Autowired
    private ShopMapper shopMapper;

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private CategoryService categoryService;

    private List<Product> setSize(List<Product> productList) {
        for (Product product : productList) {
            String[] arr = product.getProductSize().split(",");
            if (StringUtils.isNotBlank(arr[0])) {
                product.setLong(BigDecimal.valueOf(Double.parseDouble(arr[0])));
            }
            if (StringUtils.isNotBlank(arr[1])) {
                product.setWide(BigDecimal.valueOf(Double.parseDouble(arr[1])));
            }
            if (StringUtils.isNotBlank(arr[2])) {
                product.setHigh(BigDecimal.valueOf(Double.parseDouble(arr[2])));
            }
        }
        return productList;
    }

    private List<CategoryVo> selectCategorParent(List<CategoryVo> categoryVoList, Integer cartegoid) {
        CategoryVo categoryVo = new CategoryVo();
        Category category = categoryMapper.selectByPrimaryKey(cartegoid);
        if (category != null && !"".equals(category)) {
            categoryVo.setId(category.getId());
            categoryVo.setName(category.getName());
            categoryVo.setCategoryLevel(category.getParentId());
            categoryVoList.add(categoryVo);
            selectCategorParent(categoryVoList, category.getParentId());
        }
        return categoryVoList;
    }

    private List<String> selectImgList(Product product) {
        List<String> imgList = new ArrayList<>();
        if (StringUtils.isNotBlank(product.getProductSubimg())) {
            String[] arr = product.getProductSubimg().split(",");
            for (int i = 0; i < arr.length; i++) {
                imgList.add(arr[i]);
            }
        }
        return imgList;
    }

    //根據产品ID查詢产品

    public ServerResponse<ProductDetailVo> manageProductDetail(Integer productId){
        if(productId == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(),ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        Product product = productMapper.selectByPrimaryKey(productId);
        if(product == null){
            return ServerResponse.createByErrorMessage("产品已下架或者删除");
        }
        List<Product> products = Lists.newArrayList();
        products.add(product);
        this.setSize(products);
        List<String> imgList = this.selectImgList(product);
        product.setImglist(imgList);
        ProductDetailVo productDetailVo = assembleProductDetailVo(product);
        return ServerResponse.createBySuccess(productDetailVo);
    }

    private ProductDetailVo assembleProductDetailVo(Product product){
        ProductDetailVo productDetailVo = new ProductDetailVo();
        productDetailVo.setAdminId(product.getAdminId());
        productDetailVo.setProductId(product.getProductId());
        productDetailVo.setProductTitle(product.getProductTitle());
        productDetailVo.setProductSubtitle(product.getProductSubtitle());
        productDetailVo.setProductPromotion(product.getProductPromotion());
        productDetailVo.setProductWeight(product.getProductWeight());
        productDetailVo.setProductPrice(product.getProductPrice());
        productDetailVo.setProductStatus(product.getProductStatus());
        productDetailVo.setProductStock(product.getProductStock());
        productDetailVo.setProductFirstimg(product.getProductFirstimg());
        productDetailVo.setProductDesc(product.getProductDesc());
        productDetailVo.setProductSubimg(product.getProductSubimg());
        productDetailVo.setProductSize(product.getProductSize());
        productDetailVo.setLong(product.getLong());
        productDetailVo.setWide(product.getWide());
        productDetailVo.setHigh(product.getHigh());
        productDetailVo.setIdCode(product.getIdCode());
        productDetailVo.setEngineType(product.getEngineType());
        productDetailVo.setPartsNo(product.getPartsNo());
        productDetailVo.setSystemNo(product.getSystemNo());
        productDetailVo.setPartsSerialNo(product.getPartsSerialNo());

        productDetailVo.setPartsNoTwo(product.getPartsNoTwo());
        productDetailVo.setPartsNoThree(product.getPartsNoThree());
        productDetailVo.setPartsNoFour(product.getPartsNoFour());
        productDetailVo.setUnit(product.getUnit());
        productDetailVo.setDescEnglish(product.getDescEnglish());
        productDetailVo.setProductBrand(product.getProductBrand());

        productDetailVo.setStockStatus(product.getStockStatus());
        productDetailVo.setPicketLine(product.getPicketLine());

        productDetailVo.setImglist(product.getImglist());
        Shop shop = shopMapper.selectByAdminId(product.getAdminId());
        if (shop != null) {
            ShopDetailVo shopDetailVo = assembleShopDetailVo(shop);
            productDetailVo.setShopDetailVo(shopDetailVo);
        }

        productDetailVo.setImageHost(PropertiesUtil.getProperty("ftp.server.http.prefix"));
        List<CategoryVo> categoryVoList = Lists.newArrayList();
        categoryVoList = this.selectCategorParent(categoryVoList, product.getProductCategoryid());
        productDetailVo.setCategoryVoList(categoryVoList);
        Category category = categoryMapper.selectByPrimaryKey(product.getProductCategoryid());
        if(category == null){
            productDetailVo.setParentCategoryId(0);//默认根节点
        }else{
            productDetailVo.setCategory(category);
            productDetailVo.setParentCategoryId(category.getParentId());
        }

        productDetailVo.setCreateTime(DateTimeUtil.dateToStr(product.getCreatetime()));
        productDetailVo.setUpdateTime(DateTimeUtil.dateToStr(product.getEndtime()));
        return productDetailVo;
    }

    private ShopDetailVo assembleShopDetailVo(Shop shop) {
        ShopDetailVo shopDetailVo = new ShopDetailVo();
        shopDetailVo.setAdminId(shop.getAdminId());
        shopDetailVo.setShopAddress(shop.getShopAddress());
        shopDetailVo.setShopDesc(shop.getShopDesc());
        shopDetailVo.setShopEmail(shop.getShopEmail());
        shopDetailVo.setShopFirstimg(shop.getShopFirstimg());
        shopDetailVo.setShopHeadimg(shop.getShopHeadimg());
        shopDetailVo.setShopName(shop.getShopName());
        shopDetailVo.setShopPhone(shop.getShopPhone());
        shopDetailVo.setShopId(shop.getShopId());
        shopDetailVo.setShopAccount(shop.getShopAccount());
        shopDetailVo.setShopTaxCard(shop.getShopTaxCard());
        shopDetailVo.setShopSppkp(shop.getShopSppkp());
        shopDetailVo.setShopLicence(shop.getShopLicence());
        shopDetailVo.setShopProxyCertificate(shop.getShopProxyCertificate());
        shopDetailVo.setShopStatus(shop.getShopStatus());
        List<String> subimgList = Lists.newArrayList();
        if(StringUtils.isNotBlank(shop.getShopSubimg())){
            String[] subImageArray = shop.getShopSubimg().split(",");
            for (String img : subImageArray) {
                subimgList.add(img);
            }
        }
        shopDetailVo.setShopSubimg(subimgList);

        shopDetailVo.setCreateTime(DateTimeUtil.dateToStr(shop.getCreateTime()));
        shopDetailVo.setUpdateTime(DateTimeUtil.dateToStr(shop.getUpdateTime()));
        return shopDetailVo;
    }


    //查詢产品
    public ServerResponse<List<Product>> selectProductList(Integer status,Integer stockStatus) {
        List<Product> productList = productMapper.selectAll(status,stockStatus);
        List<CategoryVo> categoryVoList = new ArrayList<>();
        for (Product product : productList) {
            categoryVoList = this.selectCategorParent(categoryVoList, product.getProductCategoryid());
            product.setCategoryVoList(categoryVoList);
            product.setImglist(this.selectImgList(product));
            Shop shop = shopMapper.selectByAdminId(product.getAdminId());
            if (shop != null) {
                ShopDetailVo shopDetailVo = assembleShopDetailVo(shop);
                product.setShopDetailVo(shopDetailVo);
            }
        }
        setSize(productList);
        return ServerResponse.createBySuccess(productList);
    }

    //查詢产品
    public ServerResponse<List<Product>> selectProductList(Integer AdminId,Integer status,Integer stockStatus) {
        if (AdminId ==null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(), ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        List<Product> productList = productMapper.selectByAdminId(AdminId,status,stockStatus);
        List<CategoryVo> categoryVoList = new ArrayList<>();
        for (Product product : productList) {
            categoryVoList = this.selectCategorParent(categoryVoList, product.getProductCategoryid());
            product.setCategoryVoList(categoryVoList);
            product.setImglist(this.selectImgList(product));
            Shop shop = shopMapper.selectByAdminId(product.getAdminId());
            if (shop != null) {
                ShopDetailVo shopDetailVo = assembleShopDetailVo(shop);
                product.setShopDetailVo(shopDetailVo);
            }
        }
        setSize(productList);
        return ServerResponse.createBySuccess(productList);
    }


    //根据ID删除产品
    public ServerResponse<String> deleteProductByID(Integer productId) {
        if (StringUtils.isBlank(String.valueOf(productId))) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(), ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        int i = productMapper.deleteByPrimaryKey(productId);

        if (i > 0) {
            return ServerResponse.createBySuccessMessage("删除成功");
        }
        return ServerResponse.createByErrorMessage("删除失败");
    }

//新增产品
//更新产品
    public ServerResponse saveOrUpdateProduct(Integer adminId, Product product) {
        if (product != null) {
            if (product.getHigh()== null )
                product.setHigh(new BigDecimal(1));
            if (product.getLong()== null )
                product.setLong(new BigDecimal(1));
            if (product.getWide()== null )
                product.setWide(new BigDecimal(1));
            product.setProductSize(product.getLong() + "," + product.getWide() + "," + product.getHigh());
            if (StringUtils.isNotBlank(product.getProductSubimg())) {
                String[] subImageArray = product.getProductSubimg().split(",");
                if (subImageArray.length > 0) {
                    product.setProductFirstimg(subImageArray[0]);
                }
            }
            product.setAdminId(adminId);
            if (product.getProductId() != null) {
                Product product1 = productMapper.selectByPrimaryKey(product.getProductId());
                if (product.getIdCode()!= null ) {
                    if (!product.getIdCode().equals(product1.getIdCode())) {
                        List<Product> products = productMapper.selectByAdminIdIdCode(adminId,product.getIdCode());
                        if (products.size()>0)
                            return ServerResponse.createByErrorMessage("设备唯一标识码不能重复");
                        }
                    }
                int rowCount = productMapper.updateByPrimaryKey(product);
                if (rowCount > 0) {
                    return ServerResponse.createBySuccess("更新产品成功");
                }
                return ServerResponse.createBySuccess("更新产品失败");
            } else {
                if (product.getIdCode() == null) {
                    return ServerResponse.createByErrorMessage("请填写设备唯一标识码");
                }
                List<Product> products = productMapper.selectByAdminIdIdCode(adminId,product.getIdCode());
                if (products.size()>0){
                    return ServerResponse.createByErrorMessage("设备唯一标识码不能重复");
                }
                product.setProductStock(0);
                int rowCount = productMapper.insertSelective(product);
                if (rowCount > 0) {
                    return ServerResponse.createBySuccess("新增产品成功");
                }
                return ServerResponse.createBySuccess("新增产品失败");
            }
        }
        return ServerResponse.createByErrorMessage("新增或更新产品参数不正确");
    }

    //警戒线
    public ServerResponse<List<Product>> selectCountMin(int Min) {//判断参数正确
        if (StringUtils.isBlank(String.valueOf(Min))) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(), ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        List<Product> productList = productMapper.selectCountMin(Min);
        if (CollectionUtils.isNotEmpty(productList)) {
            for (Product product : productList) {
                setSize(productList);
            }
        }
        return ServerResponse.createBySuccess(null);
    }


    //上下架
    public ServerResponse<String> setSaleStatus(Integer status,Integer productId){
        if(productId == null || status == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(),ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        Product product = new Product();
        product.setProductId(productId);
        product.setProductStatus(status);
        int rowCount = productMapper.updateByPrimaryKeySelective(product);
        if(rowCount > 0){
            return ServerResponse.createBySuccess("修改产品销售状态成功");
        }
        return ServerResponse.createByErrorMessage("修改产品销售状态失败");
    }

    public ServerResponse<PageInfo> getProductByKeywordCategory(Integer userId,String productKeyword, Integer categoryId, String categoryKeyword, int pageNum, int pageSize, String order, String by) {
        List<Integer> categoryIdList = new ArrayList<Integer>();
        if (categoryId != null) {
            Category category = categoryMapper.selectByPrimaryKey(categoryId);
            if (category != null) {
                categoryIdList = categoryService.selectCategoryAndChildrenById(category.getId()).getData();
            }
        }

        PageHelper.startPage(pageNum, pageSize);

        List<Product> productList = Lists.newArrayList();
        if (StringUtils.isNotBlank(productKeyword)) {
            productKeyword = new StringBuilder().append("%").append(productKeyword).append("%").toString();
             productList = productMapper.selectByProductKeywordAndCategoryIds( productKeyword, categoryIdList.size() == 0 ? null : categoryIdList,order,by);
        } else if (StringUtils.isNotBlank(categoryKeyword)) {
            categoryKeyword = new StringBuilder().append("%").append(categoryKeyword).append("%").toString();
            List<Category> categoryList = categoryMapper.selectByCategoryKeyword(categoryKeyword);
            Set<Category> categorySet = Sets.newHashSet();
            for (Category category : categoryList) {
                categoryService.findChildCategory(categorySet,category.getId());
            }
            for (Category category : categorySet) {
                categoryIdList.add(category.getId());
            }
            if(categoryIdList.size() > 0){
                productList = productMapper.selectByCategoryKeywordAndCategoryIds( null, categoryIdList.size() == 0 ? null : categoryIdList,order,by);
            }
        }else {
            productList = productMapper.selectByProductKeywordAndCategoryIds(StringUtils.isBlank(productKeyword) ? null : productKeyword, categoryIdList.size() == 0 ? null : categoryIdList,order,by);
        }
        List<ProductListVo> productListVos = Lists.newArrayList();
        for (Product product : productList) {
            product.setImglist(this.selectImgList(product));
            List<CategoryVo> categoryVoList = new ArrayList<>();
            product.setCategoryVoList(this.selectCategorParent(categoryVoList, product.getProductCategoryid()));
        }
        this.setSize(productList);
        for (Product product : productList) {
            productListVos.add(this.assembleProductListVo(userId,product));
        }
        PageInfo pageInfo = new PageInfo(productList);
        pageInfo.setList(productListVos);
        return ServerResponse.createBySuccess(pageInfo);

    }

    public ProductListVo assembleProductListVo(Integer userId,Product product) {
        ProductListVo productListVo = new ProductListVo();
        productListVo.setProductId(product.getProductId());
        productListVo.setAdminId(product.getAdminId());
        productListVo.setProductStatus(product.getProductStatus());
        productListVo.setProductSubtitle(product.getProductSubtitle());
        productListVo.setProductPrice(product.getProductPrice());
        productListVo.setProductFirstimg(product.getProductFirstimg());
        productListVo.setCatagoryVoList(product.getCategoryVoList());
        productListVo.setProductTitle(product.getProductTitle());
        productListVo.setHigh(product.getHigh());
        productListVo.setWide(product.getWide());
        productListVo.setLong(product.getLong());
        productListVo.setProductWeight(product.getProductWeight());
        productListVo.setCreatetime(DateTimeUtil.dateToStr(product.getCreatetime()));
        productListVo.setEndtime(DateTimeUtil.dateToStr(product.getEndtime()));
        productListVo.setProductStatus(product.getProductStatus());
        productListVo.setProductStock(product.getProductStock());
        productListVo.setProductPromotion(product.getProductPromotion());
        productListVo.setIdCode(product.getIdCode());
        productListVo.setEngineType(product.getEngineType());
        productListVo.setPartsNo(product.getPartsNo());
        productListVo.setSystemNo(product.getSystemNo());
        productListVo.setPartsSerialNo(product.getPartsSerialNo());

        productListVo.setPartsNoTwo(product.getPartsNoTwo());
        productListVo.setPartsNoThree(product.getPartsNoThree());
        productListVo.setPartsNoFour(product.getPartsNoFour());
        productListVo.setUnit(product.getUnit());
        productListVo.setDescEnglish(product.getDescEnglish());
        productListVo.setProductBrand(product.getProductBrand());

        productListVo.setStockStatus(product.getStockStatus());
        productListVo.setStockStatusDesc(Const.ProductStockStatusEnum.codeOf(product.getStockStatus()).getValue());
        productListVo.setPicketLine(product.getPicketLine());

        Cart cart = cartMapper.selectCartByUserIdProductId(userId,product.getProductId());
        if (cart != null) {
            productListVo.setInCartQuantity(cart.getAmount());
        }

        return productListVo;
    }


    public ServerResponse<List<Product>> selectAllByid(int id) {
        if (StringUtils.isBlank(String.valueOf(id))) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(), ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        List<Integer> idlist = categoryService.selectCategoryAndChildrenByIdList(id);
        List<Product> productList = productMapper.selectByProductKeywordAndCategoryIds(null,idlist,null,null);
        this.setSize(productList);
        for (Product products : productList) {
            products.setImglist(this.selectImgList(products));
            List<CategoryVo> categoryVoList = new ArrayList<>();
            products.setCategoryVoList(this.selectCategorParent(categoryVoList, products.getProductCategoryid()));
        }
        return ServerResponse.createBySuccess(productList);
    }


}
