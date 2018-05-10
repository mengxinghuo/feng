package com.truck.dao;

import com.truck.pojo.StockAlteration;
import com.truck.pojo.StockAlteration;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StockAlterationMapper {
    int deleteByPrimaryKey(Integer alterationId);

    int insert(StockAlteration record);

    int insertSelective(StockAlteration record);

    StockAlteration selectByPrimaryKey(Integer alterationId);

    int updateByPrimaryKeySelective(StockAlteration record);

    int updateByPrimaryKey(StockAlteration record);

    List<StockAlteration> selectAllStockAlteration(@Param("adminId") Integer adminId,@Param("status") Integer status);

    Integer selectNumAlterationByAdminId(@Param("adminId") Integer adminId,@Param("status") Integer status);

    List<StockAlteration> selectStockAlterationByProductIdAdminIdWarehouseId(@Param("adminId") Integer adminId,
                                                                             @Param("productId") Integer productId,
                                                                             @Param("warehouseId") Integer warehouseId,
                                                                             @Param("status") Integer status,
                                                                             @Param("searchDate") String searchDate,
                                                                             @Param("beginDate") String beginDate,
                                                                             @Param("endDate") String endDate
                                                                             );

    Integer selectNumAlterationByProductIdAdminIdWarehouseId(@Param("adminId") Integer adminId,
                                                                             @Param("productId") Integer productId,
                                                                             @Param("warehouseId") Integer warehouseId,
                                                                             @Param("status") Integer status,
                                                                             @Param("searchDate") String searchDate,
                                                                             @Param("beginDate") String beginDate,
                                                                             @Param("endDate") String endDate
                                                                             );

    /*List<StockAlteration> selectByUserInfo(@Param("userInfoId") Integer userInfoId);

    int deleteByUserInfoId(@Param("userInfoId") Integer userInfoId);*/
}