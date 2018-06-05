package com.truck.controller.portal;

import com.truck.common.Const;
import com.truck.common.ResponseCode;
import com.truck.common.ServerResponse;
import com.truck.pojo.User;
import com.truck.service.IOrderService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private IOrderService iOrderService;

    /**
     * 创建订单
     * @param session
     * @param shippingId
     * @return
     */
    @RequestMapping("create_order.do")
    @ResponseBody
    public ServerResponse createOrder(HttpSession session, Integer shippingId,
                                      @RequestParam(value = "paymentType",required = false)Integer paymentType){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if(user == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
        }
        return iOrderService.createOrder(user.getUserId(),shippingId,paymentType);
    }

    /**
     * 取消订单
     * @param session
     * @param orderDetailId
     * @return
     */
    @RequestMapping("cancel_order.do")
    @ResponseBody
    public ServerResponse cancelOrder(HttpSession session, Integer orderDetailId){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if(user == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
        }
        return iOrderService.cancel(user.getUserId(),orderDetailId);
    }

    /**
     * 订单购物车商品预览
     * @param session
     * @return
     */
    @RequestMapping("get_order_cart_product.do")
    @ResponseBody
    public ServerResponse getOrderCartProduct(HttpSession session){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if(user == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
        }
        return iOrderService.getOrderCartProduct(user.getUserId());
    }

    /**
     * 根据订单编号查询订单详情
     * @param session
     * @param orderNo
     * @return
     */
    @RequestMapping("get_order_detail.do")
    @ResponseBody
    public ServerResponse getOrderDetail(HttpSession session, String orderNo){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if(user == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
        }
        return iOrderService.getOrderDetail(user.getUserId(),orderNo);
    }

    /**
     * 获取当前用户的所有订单
     * @param session
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("get_order_list.do")
    @ResponseBody
    public ServerResponse orderList(HttpSession session, @RequestParam(value = "pageNum",defaultValue = "1") int pageNum, @RequestParam(value = "pageSize",defaultValue = "10") int pageSize){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if(user == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
        }
        return iOrderService.getOrderList(user.getUserId(),pageNum,pageSize);
    }


}
