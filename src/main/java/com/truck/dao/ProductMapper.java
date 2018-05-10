package com.truck.dao;

import com.truck.pojo.Product;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ProductMapper {

    int deleteByPrimaryKey(Integer productId);

    int insert(Product record);

    int insertSelective(Product record);

    Product selectByPrimaryKey(@Param("productId") Integer productId);

    List<Product> selectAll();

    List<Product> selectByAdminId(@Param("adminId") Integer adminId);

    List<Product> selectByCaAID(@Param("productId") Integer productId);

    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKey(Product record);

    //警戒线查询
    List<Product> selectCountMin(int Min);

    List<Product> selectByNameAndProductId(@Param("productName") String productName, @Param("productId") Integer productId);

    List<Product> selectByProductKeywordAndCategoryIds(@Param("productKeyword") String productKeyword, @Param("categoryIdList") List<Integer> categoryIdList,@Param("order") String order,@Param("by") String by);

    List<Product> selectByCategoryKeywordAndCategoryIds(@Param("categoryKeyword") String categoryKeyword, @Param("categoryIdList") List<Integer> categoryIdList,@Param("order") String order,@Param("by") String by);

}