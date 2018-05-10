package com.truck.controller.backend;

import com.truck.common.Const;
import com.truck.common.ResponseCode;
import com.truck.common.ServerResponse;
import com.truck.pojo.Admin;
import com.truck.service.IAdminService;
import com.truck.service.IBalanceAlterationService;
import com.truck.service.IStockAlterationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/manage/stock_alteration/")
public class StockAlterationManageController {

    @Autowired
    private IStockAlterationService iStockAlterationService;

    /**
     * 获取所有变动记录
     * @param session
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("stock_alteration_list.do")
    @ResponseBody
    public ServerResponse stockAlterationList(HttpSession session,
                                                @RequestParam(value = "status", required = false) Integer status,
                                                @RequestParam(value = "pageNum",defaultValue = "1") int pageNum,
                                                @RequestParam(value = "pageSize",defaultValue = "10") int pageSize){
        Admin admin = (Admin)session.getAttribute(Const.CURRENT_ADMIN);
        if(admin == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"管理员用户未登录，请登录");
        }
            return iStockAlterationService.stockAlterationList(admin.getAdminId(),pageNum, pageSize,status);
    }

    /**
     * 根据产品获取库存变动记录
     * @param session
     * @param productId
     * @param pageNum
     * @param pageSize
     * @return
     */

    @RequestMapping("get_list_by_product.do")
    @ResponseBody
    public ServerResponse getListByUserInfo(HttpSession session,
                                            Integer productId,
                                            @RequestParam(value = "warehouseId",required = false)Integer warehouseId,
                                            @RequestParam(value = "status", required = false) Integer status,
                                            @RequestParam(value = "searchDate", required = false)String searchDate,
                                            @RequestParam(value = "beginDate", required = false)String beginDate,
                                            @RequestParam(value = "endDate", required = false)String endDate,
                                            @RequestParam(value = "pageNum",defaultValue = "1") int pageNum,
                                            @RequestParam(value = "pageSize",defaultValue = "10") int pageSize){
        Admin admin = (Admin)session.getAttribute(Const.CURRENT_ADMIN);
        if(admin == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"管理员用户未登录，请登录");
        }
            return  iStockAlterationService.getListByProductId(admin.getAdminId(),productId,warehouseId,status ,searchDate,beginDate,endDate,pageNum, pageSize);
    }

}
