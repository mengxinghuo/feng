package com.truck.dao;


import com.truck.pojo.ProductComponent;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductComponentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ProductComponent record);

    int insertSelective(ProductComponent record);

    ProductComponent selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProductComponent record);

    int updateByPrimaryKey(ProductComponent record);

    List<ProductComponent> listComponentByProductId(Integer productId);

    int deleteByComponentId( @Param("componentId") Integer componentId);

    int updateByProductComponent(ProductComponent productComponent);
}