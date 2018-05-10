package com.truck.controller.portal;

import com.truck.common.Const;
import com.truck.common.ResponseCode;
import com.truck.common.ServerResponse;
import com.truck.pojo.User;
import com.truck.service.ICartService;
import com.truck.vo.CartVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/cart/")
public class CartController {

    @Autowired
    ICartService iCartService;


    @RequestMapping("list.do")
    @ResponseBody
    public ServerResponse<CartVo> list(HttpSession session){
        User user=(User)session.getAttribute(Const.CURRENT_USER);
        if (user==null)
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getDesc());

        return iCartService.list(user.getUserId());
    }

    @RequestMapping("add.do")
    @ResponseBody
    public ServerResponse<CartVo> add(HttpSession session, Integer count, Integer productId){
        User user=(User)session.getAttribute(Const.CURRENT_USER);
        if (user==null)
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getDesc());

        return iCartService.add(user.getUserId(),productId,count);
    }


    @RequestMapping("update.do")
    @ResponseBody
    public ServerResponse<CartVo> update(HttpSession session, Integer count, Integer productId){
        User user=(User)session.getAttribute(Const.CURRENT_USER);
        if (user==null)
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getDesc());

        return iCartService.update(user.getUserId(),count,productId);
    }

    @RequestMapping("deleteProduct.do")
    @ResponseBody
    public ServerResponse<CartVo> deleteProduct(HttpSession session, String productIds){
        User user=(User)session.getAttribute(Const.CURRENT_USER);
        if (user==null)
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getDesc());
        return iCartService.deleteProduct(user.getUserId(),productIds);
    }


    @RequestMapping("selectAll.do")
    @ResponseBody
    public ServerResponse<CartVo> selectAll(HttpSession session){
        User user=(User)session.getAttribute(Const.CURRENT_USER);
        if (user==null)
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getDesc());
        return iCartService.selectOrUnSelect(user.getUserId(),Const.Cart.CHECKED,null);
    }

    @RequestMapping("unSelectAll.do")
    @ResponseBody
    public ServerResponse<CartVo> unSelectAll(HttpSession session){
        User user=(User)session.getAttribute(Const.CURRENT_USER);
        if (user==null)
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getDesc());
        return iCartService.selectOrUnSelect(user.getUserId(), Const.Cart.UN_CHECKED,null);

    }

    @RequestMapping("select.do")
    @ResponseBody
    public ServerResponse<CartVo> select(HttpSession session,Integer productId){
        User user=(User)session.getAttribute(Const.CURRENT_USER);
        if (user==null)
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getDesc());
        return iCartService.selectOrUnSelect(user.getUserId(), Const.Cart.CHECKED,productId);

    }

    @RequestMapping("unSelect.do")
    @ResponseBody
    public ServerResponse<CartVo> unSelect(HttpSession session,Integer productId){
        User user=(User)session.getAttribute(Const.CURRENT_USER);
        if (user==null)
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getDesc());
        return iCartService.selectOrUnSelect(user.getUserId(), Const.Cart.UN_CHECKED,productId);

    }

    @RequestMapping("getcartCount.do")
    @ResponseBody
    public ServerResponse<Integer> getcartCount(HttpSession session){
        User user=(User)session.getAttribute(Const.CURRENT_USER);
        if (user==null)
            return ServerResponse.createBySuccess(0);
        return iCartService.getcartCount(user.getUserId());

    }
}
