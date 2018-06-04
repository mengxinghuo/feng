package com.truck.dao;

import com.truck.pojo.Stock;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StockMapper {
    int deleteByPrimaryKey(Integer stockId);

    int insert(Stock record);

    int insertSelective(Stock record);

    Stock selectByPrimaryKey(Integer stockId);

    Stock selectByStockIdAndProductId(@Param("stockId")Integer stockId,@Param("productId")Integer productId);

    List<Stock> selectListByProductIdWarehouseId(@Param("adminId")Integer adminId,@Param("productId")Integer productId,@Param("warehouseId")Integer warehouseId,@Param("stockStatus")Integer stockStatus);

    List<Stock> selectListByWarehouseId(Integer warehouseId);

    int updateByPrimaryKeySelective(Stock record);

    int updateByPrimaryKey(Stock record);
}