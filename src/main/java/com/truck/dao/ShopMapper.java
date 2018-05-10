package com.truck.dao;

import com.truck.pojo.Admin;
import com.truck.pojo.Shop;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ShopMapper {
    int deleteByPrimaryKey(Integer shopId);

    int deleteByAdminId(Integer adminId);

    int insert(Shop record);

    int selectShopIdByadminId(@Param("adminId") Integer adminId);

    int insertSelective(Shop record);

    Shop selectByPrimaryKey(Integer shopId);

    int updateByPrimaryKeySelective(Shop record);

    int updateByPrimaryKey(Shop record);

    List<Shop> selectList(@Param("shopStatus") Integer shopStatus);

    List<Shop> selectByStatusReviewed();

    Shop selectByAdminId(Integer adminId);

    List<Shop> selectByNameAndShopId(@Param("shopName") String shopName, @Param("shopId") Integer shopId);

    List<Shop> selectByNameAndShopIdAllStatus(@Param("shopName") String shopName, @Param("shopId") Integer shopId);

    List<Shop> selectByNameAndShopIdAdminId(@Param("adminId") Integer adminId, @Param("shopName") String shopName, @Param("shopId") Integer shopId);

    List<Shop> selectShopListByUserId(@Param("userId") Integer userId);

    List<Shop> selectShopListByOrderId(@Param("orderId") Integer orderId);

    int checkShopName(@Param("shopName") String shopName);

    int checkShopEmail(@Param("shopEmail") String shopEmail);

    int checkShopNum(@Param("adminId") Integer adminId);

    List<Shop> selectShopIdByGroupCart(@Param("userId") Integer userId);


}