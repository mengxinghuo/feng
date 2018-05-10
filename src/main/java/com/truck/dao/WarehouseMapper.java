package com.truck.dao;

import com.truck.pojo.Contact;
import com.truck.pojo.Warehouse;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WarehouseMapper {
    int deleteByPrimaryKey(Integer warehouseId);

    int insert(Warehouse record);

    int insertSelective(Warehouse record);

    Warehouse selectByPrimaryKey(Integer warehouseId);

    int updateByPrimaryKeySelective(Warehouse record);

    int updateByPrimaryKey(Warehouse record);

    List<Warehouse> selectByShopId(@Param("shopId") Integer shopId);
}