package com.truck.dao;

import com.truck.pojo.StockCategory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StockCategoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(StockCategory record);

    int insertSelective(StockCategory record);

    StockCategory selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(StockCategory record);

    int updateByPrimaryKey(StockCategory record);

    List<StockCategory> selectStockCategoryChildrenByParentId(Integer parentId);

    int deleteByPrimaryKeyByIdList(@Param("integers") List<Integer> integers);
}