package com.truck.service;

import com.truck.common.ServerResponse;
import com.truck.vo.CartVo;

public interface ICartService {

    ServerResponse<CartVo> list(Integer userId);

    ServerResponse<CartVo> add(Integer userId, Integer productId, Integer count);

    ServerResponse<CartVo> update(Integer userId, Integer count, Integer productId);

    ServerResponse<CartVo> deleteProduct(Integer userId,String productIds);

    ServerResponse<CartVo> selectOrUnSelect(Integer userId, Integer checked, Integer productId);

    ServerResponse<Integer> getcartCount(Integer userId);
}
