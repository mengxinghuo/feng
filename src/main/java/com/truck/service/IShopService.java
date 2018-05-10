package com.truck.service;

import com.github.pagehelper.PageInfo;
import com.truck.common.ServerResponse;
import com.truck.pojo.Shop;
import com.truck.vo.ShopDetailVo;

/**
 * Created by geely
 */
public interface IShopService {

    ServerResponse saveOrUpdateShop(Integer adminId, Shop shop);

    ServerResponse<String> checkValid(String str, String type);

    ServerResponse<PageInfo> getShopList(Integer shopStatus, int pageNum, int pageSize);

    ServerResponse<PageInfo> getShopReviewedList( int pageNum, int pageSize);

    ServerResponse<PageInfo> searchShop(String shopName, Integer shopId, int pageNum, int pageSize);

    ServerResponse<PageInfo> searchShopAll( String shopName, Integer shopId, int pageNum, int pageSize);

    ServerResponse<ShopDetailVo> getShopDetail(Integer shopId);

    ServerResponse<ShopDetailVo> getShopDetailSelf(Integer adminId);

    ServerResponse<String> deleteByAdminId(Integer adminId);

    ServerResponse<String> setShopStatus(Integer shopId,Integer shopStatus);

}
