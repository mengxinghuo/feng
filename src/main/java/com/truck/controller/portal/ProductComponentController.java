package com.truck.controller.portal;

import com.github.pagehelper.PageInfo;
import com.truck.common.ServerResponse;
import com.truck.service.IProductComponentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;


@Controller
@RequestMapping("/product/component/")
public class ProductComponentController {

    @Autowired
    private IProductComponentService productComponentService;

    @RequestMapping("list.do")
    @ResponseBody
    public ServerResponse<PageInfo> list(HttpSession session,
                                         @RequestParam(value = "pageNum",defaultValue = "1") int pageNum,
                                         @RequestParam(value = "pageNum",defaultValue = "10") int pageSize,
                                         Integer productId){
        return productComponentService.listComponentByProductId(productId,pageNum,pageSize);
    }

}