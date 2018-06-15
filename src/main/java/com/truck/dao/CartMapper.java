package com.truck.dao;

import com.truck.pojo.Cart;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CartMapper {
    int deleteByPrimaryKey(Integer cartId);

    int insert(Cart record);

    int insertSelective(Cart record);

    Cart selectByPrimaryKey(Integer cartId);

    int updateByPrimaryKeySelective(Cart record);

    int updateByPrimaryKey(Cart record);

    Cart selectCartByUserIdProductId(@Param("userId") Integer userId,@Param("productId") Integer productId);

    List<Cart> selectCartByUserId(Integer userId);

    int selectCartProductCheckedStatusByUserId(Integer userId);

    int deleteByUserIdProductIds(@Param("userId")Integer userId,@Param("productIdList")List<String> productIdList);

    int deleteByUserId(@Param("userId")Integer userId);

    int checkedOrUncheckedProduct(@Param("userId")Integer userId,@Param("productId")Integer productId,@Param("checked") Integer checked);

    int selectCartProductCount(Integer userId);

    List<Cart> selectCheckedCartByUserId(@Param("userId") Integer userId);

    List<Cart> selectCheckedCartByUserIdShopId(@Param("userId") Integer userId,@Param("shopId") Integer shopId);

    List<Cart> selectCartByAdminId(@Param("adminId") Integer adminId,@Param("userId") Integer userId);
}