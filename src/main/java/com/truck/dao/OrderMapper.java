package com.truck.dao;

import com.truck.pojo.Order;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderMapper {
    int deleteByPrimaryKey(Integer orderId);

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(Integer orderId);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);

    Order selectByUserIdAndOrderNo(@Param("userId") Integer userId, @Param("orderNo") String orderNo);

    List<Order> selectByUserId(@Param("userId") Integer userId);

    List<Order> selectAllOrder();

    List<Order> selectOrderByShopId(@Param("shopId") Integer shopId);

    List<Order> searchByOrderNo(@Param("orderNo")String orderNo);

    List<Order> selectOrderByShopIdAndOrderNo(@Param("shopId") Integer shopId,@Param("orderNo") String orderNo);

    Order selectByOrderNo(@Param("orderNo")String orderNo);

    List<Order> selectPurchaseByUserId(@Param("userId") Integer userId);
}