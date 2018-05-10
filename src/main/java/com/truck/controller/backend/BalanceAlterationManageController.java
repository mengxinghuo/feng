package com.truck.controller.backend;

import com.truck.common.Const;
import com.truck.common.ResponseCode;
import com.truck.common.ServerResponse;
import com.truck.pojo.Admin;
import com.truck.service.IAdminService;
import com.truck.service.IBalanceAlterationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/manage/balance_alteration/")
public class BalanceAlterationManageController {

    @Autowired
    private IBalanceAlterationService iBalanceAlterationService;
    @Autowired
    private IAdminService iAdminService;

    /**
     * 获取所有变动记录
     * @param session
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("balance_alteration_list.do")
    @ResponseBody
    public ServerResponse balanceAlterationList(HttpSession session,
                                                @RequestParam(value = "status",required = false)Integer status,
                                                @RequestParam(value = "pageNum",defaultValue = "1") int pageNum,
                                                @RequestParam(value = "pageSize",defaultValue = "10") int pageSize){
        Admin admin = (Admin)session.getAttribute(Const.CURRENT_ADMIN);
        if(admin == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"管理员用户未登录，请登录");
        }
        if (iAdminService.checkAdminRole(admin).isSuccess()) {
            return iBalanceAlterationService.balanceAlterationList(pageNum, pageSize,status);
        }
        return ServerResponse.createByErrorMessage("无权限操作");
    }

    /**
     * 根据用户获取账户变动记录
     * @param session
     * @param userInfoId
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("get_list_by_userInfo.do")
    @ResponseBody
    public ServerResponse getListByUserInfo(HttpSession session, Integer userInfoId,
                                            @RequestParam(value = "status",required = false)Integer status,
                                            @RequestParam(value = "pageNum",defaultValue = "1") int pageNum,
                                            @RequestParam(value = "pageSize",defaultValue = "10") int pageSize){
        Admin admin = (Admin)session.getAttribute(Const.CURRENT_ADMIN);
        if(admin == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"管理员用户未登录，请登录");
        }
        if (iAdminService.checkAdminRole(admin).isSuccess()) {
            return  iBalanceAlterationService.getListByUserInfo(userInfoId, pageNum, pageSize,status);
        }
        return ServerResponse.createByErrorMessage("无权限操作");
    }

}
