package com.truck.controller.backend;

import com.github.pagehelper.PageInfo;
import com.truck.common.Const;
import com.truck.common.ResponseCode;
import com.truck.common.ServerResponse;
import com.truck.pojo.Admin;
import com.truck.pojo.UserInfo;
import com.truck.service.IAdminService;
import com.truck.service.IUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;

@Controller
@RequestMapping("/userInfo/manage/")
public class UserInfoManageController {

    @Autowired
    private IUserInfoService iUserInfoService;
    @Autowired
    private IAdminService iAdminService;

    /**
     * 查询所有用户信息，需要管理员权限(可根据公司名进行模糊查询)
     * @param session
     * @param companyName
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("get_all_user_info.do")
    @ResponseBody
    public ServerResponse<PageInfo> getAllUserInfo(HttpSession session,
                                                   String companyName,
                                                   @RequestParam(value = "pageNum",defaultValue = "1") int pageNum,
                                                   @RequestParam(value = "pageSize",defaultValue = "10") int pageSize){
        Admin admin = (Admin)session.getAttribute(Const.CURRENT_ADMIN);
        if(admin == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"管理员用户未登录，请登录");
        }
        if (iAdminService.checkAdminRole(admin).isSuccess()) {
            return iUserInfoService.getAllUserInfo(companyName,pageNum, pageSize);
        }
        return ServerResponse.createByErrorMessage("无权限操作");
    }

    /**
     * 根据用户ID查询用户信息
     * @param session
     * @param userId
     * @return
     */
    @RequestMapping("search_by_user_id.do")
    @ResponseBody
    public ServerResponse searchByUserId(HttpSession session, Integer userId){
        Admin admin = (Admin)session.getAttribute(Const.CURRENT_ADMIN);
        if(admin == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"管理员用户未登录，请登录");
        }
        if (iAdminService.checkAdminRole(admin).isSuccess()) {
            return iUserInfoService.searchByUserId(userId);
        }
        return ServerResponse.createByErrorMessage("无权限操作");
    }

    /**
     * 充值账户金额
     * @param session
     * @param userInfoId
     * @param money
     * @return
     */
    @RequestMapping("recharge_balance.do")
    @ResponseBody
    public ServerResponse rechargeBalance(HttpSession session, Integer userInfoId, BigDecimal money){
        Admin admin = (Admin)session.getAttribute(Const.CURRENT_ADMIN);
        if(admin == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"管理员用户未登录，请登录");
        }
        if (iAdminService.checkAdminRole(admin).isSuccess()) {
            return iUserInfoService.rechargeBalance(userInfoId,money);
        }
        return ServerResponse.createByErrorMessage("无权限操作");
    }


}
