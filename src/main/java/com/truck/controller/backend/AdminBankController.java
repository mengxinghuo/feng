package com.truck.controller.backend;

import com.github.pagehelper.PageInfo;
import com.truck.common.Const;
import com.truck.common.ResponseCode;
import com.truck.common.ServerResponse;
import com.truck.pojo.Admin;
import com.truck.pojo.AdminBank;
import com.truck.pojo.Bank;
import com.truck.pojo.User;
import com.truck.service.IAdminBankService;
import com.truck.service.IBankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/manage/bank/")
public class AdminBankController {

    @Autowired
    private IAdminBankService iAdminBankService;

    @RequestMapping("add.do")
    @ResponseBody
    public ServerResponse add(HttpSession session, AdminBank adminBank){
       Admin admin = (Admin) session.getAttribute(Const.CURRENT_ADMIN);
        if (admin == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "管理员用户未登录，请登录");
        }
        return iAdminBankService.add(admin.getAdminId(),adminBank);
    }

    @RequestMapping("del.do")
    @ResponseBody
    public ServerResponse del(HttpSession session, Integer bankId){
       Admin admin = (Admin) session.getAttribute(Const.CURRENT_ADMIN);
        if (admin == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "管理员用户未登录，请登录");
        }
        return iAdminBankService.del(admin.getAdminId(),bankId);
    }

    @RequestMapping("update.do")
    @ResponseBody
    public ServerResponse update(HttpSession session, AdminBank adminBank){
       Admin admin = (Admin) session.getAttribute(Const.CURRENT_ADMIN);
        if (admin == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "管理员用户未登录，请登录");
        }
        return iAdminBankService.update(admin.getAdminId(),adminBank);
    }

    @RequestMapping("select.do")
    @ResponseBody
    public ServerResponse select(HttpSession session, Integer bankId){
       Admin admin = (Admin) session.getAttribute(Const.CURRENT_ADMIN);
        if (admin == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "管理员用户未登录，请登录");
        }
        return iAdminBankService.select(admin.getAdminId(),bankId);
    }

    @RequestMapping("list.do")
    @ResponseBody
    public ServerResponse<PageInfo> list(HttpSession session,
                                         @RequestParam(value = "pageNum",defaultValue = "1") int pageNum,
                                         @RequestParam(value = "pageNum",defaultValue = "10") int pageSize){
        Admin admin = (Admin) session.getAttribute(Const.CURRENT_ADMIN);
        if (admin == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "管理员用户未登录，请登录");
        }
        return iAdminBankService.list(admin.getAdminId(),pageNum,pageSize);
    }

}
