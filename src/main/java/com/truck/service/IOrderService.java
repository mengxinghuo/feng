package com.truck.service;

import com.github.pagehelper.PageInfo;
import com.truck.common.ServerResponse;
import com.truck.vo.OrderVo;

public interface IOrderService {

    ServerResponse createOrder(Integer userId, Integer shippingId, Integer paymentType);

    ServerResponse<String> cancel(Integer userId,Integer orderDetailId);

    ServerResponse getOrderCartProduct(Integer userId);

    ServerResponse getOrderDetail(Integer userId, String orderNo);

    ServerResponse<PageInfo> getOrderList(Integer userId, int pageNum, int pageSize);

    //backend

    ServerResponse<PageInfo> manageList(int pageNum, int pageSize);

    ServerResponse<PageInfo> manageList(Integer adminId, int pageNum, int pageSize);


    ServerResponse<PageInfo> manageSearch(String orderNo, int pageNum, int pageSize);

    ServerResponse<PageInfo> manageSearchShop(Integer adminId, String orderNo, int pageNum, int pageSize);

    ServerResponse<String> manageSendGoods(Integer orderDetailId, Integer stockId);


}
