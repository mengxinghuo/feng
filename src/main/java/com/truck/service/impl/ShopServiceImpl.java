package com.truck.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.truck.common.Const;
import com.truck.common.ResponseCode;
import com.truck.common.ServerResponse;
import com.truck.dao.ProductMapper;
import com.truck.dao.ShopMapper;
import com.truck.pojo.Company;
import com.truck.pojo.Product;
import com.truck.pojo.Shop;
import com.truck.service.IShopService;
import com.truck.service.ProductService;
import com.truck.util.DateTimeUtil;
import com.truck.util.PropertiesUtil;
import com.truck.vo.ProductListVo;
import com.truck.vo.ShopDetailVo;
import com.truck.vo.ShopListVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by geely
 */
@Service("iShopService")
public class ShopServiceImpl implements IShopService {


    @Autowired
    private ShopMapper shopMapper;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private ProductService productService;


    public ServerResponse saveOrUpdateShop(Integer adminId , Shop shop){
        if(shop != null)
        {
            if(StringUtils.isNotBlank(shop.getShopSubimg())){
                String[] subImageArray = shop.getShopSubimg().split(",");
                if(subImageArray.length > 0){
                    shop.setShopFirstimg(subImageArray[0]);
                }
            }

            if(shop.getShopId() != null){
                int rowCount = shopMapper.updateByPrimaryKeySelective(shop);
                if(rowCount > 0){
                    return ServerResponse.createBySuccess("更新店铺成功");
                }
                return ServerResponse.createBySuccess("更新店铺失败");
            }else{
                shop.setAdminId(adminId);
                shop.setShopStatus(Const.ShopStatusEnum.REVIEWING.getCode());

                ServerResponse validResponse=this.checkValid(shop.getAdminId().toString(), Const.SHOPNUM);
                if (!validResponse.isSuccess())
                    return validResponse;

                validResponse=this.checkValid(shop.getShopName(),Const.SHOPNAME);
                if (!validResponse.isSuccess())
                    return validResponse;

                validResponse=this.checkValid(shop.getShopEmail(),Const.SHOPEMAIL);
                if (!validResponse.isSuccess())
                    return validResponse;
                int rowCount = shopMapper.insert(shop);
                if(rowCount > 0){
                    Map result = Maps.newHashMap();
                    result.put("shopId",shop.getShopId());
                    return ServerResponse.createBySuccess("新增店铺成功,请等待审核",result);
                }
                return ServerResponse.createBySuccess("新增店铺失败");
            }
        }
        return ServerResponse.createByErrorMessage("新增或更新店铺参数不正确");
    }

    @Override
    public ServerResponse<String> checkValid(String str, String type) {
        if (StringUtils.isNotBlank(type)){
            //开始校验
            if (Const.SHOPNAME.equals(type)){
                int resultCount=shopMapper.checkShopName(str);
                if (resultCount>0)
                    return ServerResponse.createByErrorMessage("店铺名已存在");
            }

            if (Const.SHOPNUM.equals(type)){
                int resultCount=shopMapper.checkShopNum(Integer.parseInt(str));
                if (resultCount>0)
                    return ServerResponse.createByErrorMessage("已经有一个自己的店铺");
            }

            if (Const.SHOPEMAIL.equals(type)){
                int resultCount=shopMapper.checkShopEmail(str);
                if (resultCount>0)
                    return ServerResponse.createByErrorMessage("店铺email已存在");
            }

        }else {
            return ServerResponse.createByErrorMessage("参数错误");

        }
        return ServerResponse.createBySuccessMessage("校验成功");
    }

    private ShopDetailVo assembleShopDetailVo(Shop shop){
        ShopDetailVo shopDetailVo = new ShopDetailVo();
        shopDetailVo.setAdminId(shop.getAdminId());
        shopDetailVo.setShopAddress(shop.getShopAddress());
        shopDetailVo.setShopDesc(shop.getShopDesc());
        shopDetailVo.setShopEmail(shop.getShopEmail());
        shopDetailVo.setShopFirstimg(shop.getShopFirstimg());
        shopDetailVo.setShopHeadimg(shop.getShopHeadimg());
        shopDetailVo.setShopName(shop.getShopName());
        shopDetailVo.setShopPhone(shop.getShopPhone());
        shopDetailVo.setShopAccount(shop.getShopAccount());
        shopDetailVo.setShopTaxCard(shop.getShopTaxCard());
        shopDetailVo.setShopSppkp(shop.getShopSppkp());
        shopDetailVo.setShopLicence(shop.getShopLicence());
        shopDetailVo.setShopProxyCertificate(shop.getShopProxyCertificate());
        List<String> subimgList = Lists.newArrayList();

        List<ProductListVo> productListVoList = Lists.newArrayList();
        List<Product> productList = productMapper.selectByAdminId(shop.getAdminId(),null);

        for (int i = 0; i <  productList.size(); i++) {
            Product  product = productList.get(i);
            ProductListVo productListVo = productService.assembleProductListVo(product);
            productListVoList.add(productListVo);
        }

        if(StringUtils.isNotBlank(shop.getShopSubimg())){
            String[] subImageArray = shop.getShopSubimg().split(",");
            for (String img : subImageArray) {
                subimgList.add(img);
            }
        }

        shopDetailVo.setProductListVoList(productListVoList);
        shopDetailVo.setShopSubimg(subimgList);
        shopDetailVo.setShopId(shop.getShopId());

        shopDetailVo.setCreateTime(DateTimeUtil.dateToStr(shop.getCreateTime()));
        shopDetailVo.setUpdateTime(DateTimeUtil.dateToStr(shop.getUpdateTime()));
        return shopDetailVo;
    }

    //超管查看所有店铺
    public ServerResponse<PageInfo> getShopList(Integer shopStatus ,int pageNum, int pageSize ){
        PageHelper.startPage(pageNum,pageSize);
        List<Shop> shopList = shopMapper.selectList(shopStatus);

        List<ShopListVo> shopListVoList = Lists.newArrayList();
        for(Shop shopItem : shopList){
            ShopListVo shopListVo = assembleShopListVo(shopItem);
            shopListVoList.add(shopListVo);
        }
        PageInfo pageResult = new PageInfo(shopList);
        pageResult.setList(shopListVoList);
        return ServerResponse.createBySuccess(pageResult);
    }

    //顾客查看审核通过的店铺
    public ServerResponse<PageInfo> getShopReviewedList( int pageNum, int pageSize){
        PageHelper.startPage(pageNum,pageSize);
        List<Shop> shopList = shopMapper.selectByStatusReviewed();
        List<ShopListVo> shopListVoList = Lists.newArrayList();
        for(Shop shopItem : shopList){
            ShopListVo shopListVo = assembleShopListVo(shopItem);
            shopListVoList.add(shopListVo);
        }
        PageInfo pageResult = new PageInfo(shopList);
        pageResult.setList(shopListVoList);
        return ServerResponse.createBySuccess(pageResult);
    }

    private ShopListVo assembleShopListVo(Shop shop){
        ShopListVo shopListVo = new ShopListVo();
        shopListVo.setShopId(shop.getShopId());
        shopListVo.setAdminId(shop.getAdminId());
        shopListVo.setShopDesc(shop.getShopDesc());
        shopListVo.setShopFirstimg(shop.getShopFirstimg());
        shopListVo.setShopHeadimg(shop.getShopHeadimg());
        shopListVo.setShopStatus(shop.getShopStatus());
        List<String> subimgList = Lists.newArrayList();
        List<ProductListVo> productListVoList = Lists.newArrayList();
        List<Product> productList = productMapper.selectByAdminId(shop.getAdminId(),null);

        for (int i = 0; i < (productList.size()>3 ? 3 : productList.size()); i++) {
            Product  product = productList.get(i);
            ProductListVo productListVo = productService.assembleProductListVo(product);
            productListVoList.add(productListVo);
        }

        if(StringUtils.isNotBlank(shop.getShopSubimg())){
            String[] subImageArray = shop.getShopSubimg().split(",");
            for (String img : subImageArray) {
                subimgList.add(img);
            }
        }
        shopListVo.setShopSubimg(subimgList);
        shopListVo.setProductListVoList(productListVoList);
        shopListVo.setShopName(shop.getShopName());
        return shopListVo;
    }

    public ServerResponse<PageInfo> searchShop(String shopName, Integer shopId, int pageNum, int pageSize){
        PageHelper.startPage(pageNum,pageSize);
        if(StringUtils.isNotBlank(shopName)){
            shopName = new StringBuilder().append("%").append(shopName).append("%").toString();
        }
        List<Shop> shopList = shopMapper.selectByNameAndShopId(shopName,shopId);
        List<ShopListVo> shopListVoList = Lists.newArrayList();
        for(Shop shopItem : shopList){
            ShopListVo shopListVo = assembleShopListVo(shopItem);
            shopListVoList.add(shopListVo);
        }
        PageInfo pageResult = new PageInfo(shopList);
        pageResult.setList(shopListVoList);
        return ServerResponse.createBySuccess(pageResult);
    }

    public ServerResponse<PageInfo> searchShopAll( String shopName, Integer shopId, int pageNum, int pageSize){
        PageHelper.startPage(pageNum,pageSize);
        if(StringUtils.isNotBlank(shopName)){
            shopName = new StringBuilder().append("%").append(shopName).append("%").toString();
        }
        List<Shop> shopList = shopMapper.selectByNameAndShopIdAllStatus( shopName,shopId);
        List<ShopListVo> shopListVoList = Lists.newArrayList();
        for(Shop shopItem : shopList){
            ShopListVo shopListVo = assembleShopListVo(shopItem);
            shopListVoList.add(shopListVo);
        }
        PageInfo pageResult = new PageInfo(shopList);
        pageResult.setList(shopListVoList);
        return ServerResponse.createBySuccess(pageResult);
    }


    public ServerResponse<ShopDetailVo> getShopDetail(Integer shopId){
        if(shopId == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(), ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        Shop shop = shopMapper.selectByPrimaryKey(shopId);
        if(shop == null){
            return ServerResponse.createByErrorMessage("店铺已删除");
        }
        ShopDetailVo shopDetailVo = assembleShopDetailVo(shop);
        return ServerResponse.createBySuccess(shopDetailVo);
    }

    public ServerResponse<ShopDetailVo> getShopDetailSelf(Integer adminId){
        if(adminId == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(), ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        Shop shop = shopMapper.selectByAdminId(adminId);
        if(shop == null){
            return ServerResponse.createByErrorMessage("店铺已删除");
        }
        ShopDetailVo shopDetailVo = assembleShopDetailVo(shop);
        return ServerResponse.createBySuccess(shopDetailVo);
    }

    //根据AdminId删除产品
    public ServerResponse<String> deleteByAdminId(Integer adminId) {

        int i = shopMapper.deleteByAdminId(adminId);

        if (i > 0) {
            return ServerResponse.createBySuccessMessage("删除成功");
        }
        return ServerResponse.createByErrorMessage("删除失败");
    }

    //管理员审核
    public ServerResponse setShopStatus(Integer shopId,Integer shopStatus){
        if(shopId == null || shopStatus == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(),ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        Shop shop = shopMapper.selectByPrimaryKey(shopId);
        if(shop == null){
            return ServerResponse.createByErrorMessage("该店铺不存在");
        }

        if(Const.ShopStatusEnum.REVIEW_SUCCESS.getCode() == shopStatus){
            if (StringUtils.isBlank(shop.getShopProxyCertificate()) ||
                    StringUtils.isBlank(shop.getShopAccount()) ||
                    StringUtils.isBlank(shop.getShopSppkp()) ||
                    StringUtils.isBlank(shop.getShopTaxCard()) ||
                    StringUtils.isBlank(shop.getShopLicence())) {
                return ServerResponse.createBySuccessNeedData("公司文件资料不完整", shop);
            }
        }
        Shop shop1 = new Shop();
        shop1.setShopId( shopId);
        shop1.setShopStatus(shopStatus);
        int rowCount = shopMapper.updateByPrimaryKeySelective(shop1);
        if(rowCount > 0){
            return ServerResponse.createBySuccess("修改店铺状态成功");
        }
        return ServerResponse.createByErrorMessage("修改店铺状态失败");
    }
}


























