package com.truck.controller.portal;

import com.truck.common.Const;
import com.truck.common.ResponseCode;
import com.truck.common.ServerResponse;
import com.truck.pojo.User;
import com.truck.pojo.UserInfo;
import com.truck.service.IUserInfoService;
import com.truck.vo.UserInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/userInfo/")
public class UserInfoController {

    @Autowired
    private IUserInfoService iUserInfoService;

    /**
     * 查询用户信息
     * @param session
     * @return
     */
    @RequestMapping("get_user_info.do")
    @ResponseBody
    public ServerResponse<UserInfoVo> getUserInfo(HttpSession session){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if(user == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录，请登录");
        }
        return iUserInfoService.getUserInfo(user.getUserId());
    }

    /**
     * 查询账户变动记录
     * @param session
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("get_balanceAlteration.do")
    @ResponseBody
    public ServerResponse getBalanceAlteration(HttpSession session,
                                               @RequestParam(value = "pageNum",defaultValue = "1") int pageNum,
                                               @RequestParam(value = "pageSize",defaultValue = "10") int pageSize){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if(user == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录，请登录");
        }
        return iUserInfoService.getBalanceAlteration(user.getUserId(),pageNum,pageSize);
    }
}
