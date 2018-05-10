package com.truck.controller.backend;

import com.github.pagehelper.PageInfo;
import com.truck.common.Const;
import com.truck.common.ResponseCode;
import com.truck.common.ServerResponse;
import com.truck.pojo.Admin;
import com.truck.pojo.ProductConf;
import com.truck.service.IProductConfService;
import com.truck.vo.ProductConfVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;


@Controller
@RequestMapping("/product/conf/manage")
public class ProductConfManageController {

    @Autowired
    private IProductConfService productConfService;

    @RequestMapping("list.do")
    @ResponseBody
    public ServerResponse<PageInfo> list(HttpSession session,
                                         @RequestParam(value = "pageNum",defaultValue = "1") int pageNum,
                                         @RequestParam(value = "pageNum",defaultValue = "10") int pageSize,
                                         Integer productId,Integer componentId){
        Admin admin = (Admin) session.getAttribute(Const.CURRENT_ADMIN);
        if (admin == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "管理员用户未登录，请登录");
        }
        return productConfService.listConfByProductId(productId,componentId,pageNum,pageSize);
    }

    @RequestMapping("add.do")
    @ResponseBody
    public ServerResponse createOrder(HttpSession session, ProductConfVo productConfVo){
        Admin admin = (Admin) session.getAttribute(Const.CURRENT_ADMIN);
        if (admin == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "管理员用户未登录，请登录");
        }
        return productConfService.add(productConfVo);
    }

    @RequestMapping("del.do")
    @ResponseBody
    public ServerResponse del(HttpSession session, Integer id) {
        Admin admin = (Admin) session.getAttribute(Const.CURRENT_ADMIN);
        if (admin == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "管理员用户未登录，请登录");
        }
        return productConfService.del(id);
    }

    @RequestMapping("update.do")
    @ResponseBody
    public ServerResponse update(HttpSession session, ProductConf productConf) {
        Admin admin = (Admin) session.getAttribute(Const.CURRENT_ADMIN);
        if (admin == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "管理员用户未登录，请登录");
        }
        return productConfService.update(productConf);
    }

}