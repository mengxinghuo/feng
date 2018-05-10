package com.truck.dao;

import com.truck.pojo.ProductConf;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductConfMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ProductConf record);

    int insertSelective(ProductConf record);

    ProductConf selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProductConf record);

    int updateByPrimaryKey(ProductConf record);

    List<ProductConf> listConfByProductId(@Param("productId") Integer productId, @Param("componentId") Integer componentId);

    int deleteById( @Param("id") Integer id);

    int updateByProductConf(ProductConf productConf);
}