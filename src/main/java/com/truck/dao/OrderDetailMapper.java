package com.truck.dao;

import com.truck.pojo.OrderDetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderDetailMapper {
    int deleteByPrimaryKey(Integer orderDetailId);

    int insert(OrderDetail record);

    int insertSelective(OrderDetail record);

    OrderDetail selectByPrimaryKey(Integer orderDetailId);

    int updateByPrimaryKeySelective(OrderDetail record);

    int updateByPrimaryKey(OrderDetail record);

    int batchInsert(@Param("orderDetailList")List<OrderDetail> orderDetailList);

    List<OrderDetail> selectListByOrderId(@Param("orderId") Integer orderId);

    List<OrderDetail> getByOrderIdUserId(@Param("orderId") Integer orderId, @Param("userId") Integer userId);

    List<OrderDetail> selectOrderDetailByAdminId(@Param("orderId") Integer orderId,@Param("adminId") Integer adminId);

    int selectCountDistributionOrderDetail(@Param("orderId") Integer orderId);

    int selectCountUnCanceled(@Param("orderId") Integer orderId);

    OrderDetail selectByUserIdAndOrderDetailId(@Param("userId") Integer userId,@Param("orderDetailId") Integer orderDetailId);
}