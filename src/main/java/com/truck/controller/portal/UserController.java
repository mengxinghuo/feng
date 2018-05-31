package com.truck.controller.portal;


import com.google.common.collect.Maps;
import com.truck.common.Const;
import com.truck.common.ResponseCode;
import com.truck.common.ServerResponse;
import com.truck.pojo.User;
import com.truck.pojo.User;
import com.truck.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Map;


@Controller
@RequestMapping("/user/")
public class UserController {


    @Autowired
    private IUserService iUserService;

    /**
     * 用户登陆
     * @param userName
     * @param password
     * @param session
     * @return
     */
    @RequestMapping(value = "login.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<User> login(String userName, String password, HttpSession session){
        ServerResponse<User> response= iUserService.login(userName,password);
        if (response.isSuccess())
            session.setAttribute(Const.CURRENT_USER,response.getData());
        return response;
    }

    /**
     * 退出登陆
     * @param session
     * @return
     */
    @RequestMapping(value = "logout.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> logout(HttpSession session){
        session.removeAttribute(Const.CURRENT_USER);
        return ServerResponse.createBySuccess();
    }

    /**
     * 用户注册
     * @param user
     * @return
     */
    @RequestMapping(value = "register.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse register(HttpSession session,User user){
        String password = user.getPassword();
        ServerResponse<String> response = iUserService.register(user);
        if (response.isSuccess()){
            ServerResponse<User> responses = iUserService.login(user.getUserName(),password);
            if (responses.isSuccess()){
                User users=responses.getData();
                session.setAttribute(Const.CURRENT_USER,users);
                return responses;
            }
            return responses;
        }
        return response;
    }



    /**
     * 校验
     * @param str
     * @param type
     * @return
     */
    @RequestMapping(value = "checkValid.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> checkValid(String str,String type){
        return iUserService.checkValid(str,type);
    }

    /**
     * 获取用户登陆信息
     * @param session
     * @return
     */
    @RequestMapping(value = "getUserInfo.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<User> getUserInfo(HttpSession session){
        User user =(User) session.getAttribute(Const.CURRENT_USER);
        if (user!=null)
            return ServerResponse.createBySuccess(user);
        return ServerResponse.createByErrorMessage("用户没登陆，无法获取当前用户的信息");
    }

    /**
     *获取用户忘记密码提示问题
     * @param userName
     * @return
     */
    @RequestMapping(value = "forgetGetQuestion.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> forgetGetQuestion(String userName){
        return iUserService.selectQuestion(userName);
    }

    /**
     * 获取用户忘记密码提示问题的答案
     * @param userName
     * @param question
     * @param answer
     * @return
     */
    @RequestMapping(value = "forgetGetAnswer.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> forgetGetAnswer(String userName,String question,String answer){
        return iUserService.checkAnswer(userName,question,answer);
    }

    /**
     * 重置密码
     * @param userName
     * @param passwordNew
     * @param forgetToken
     * @return
     */
    @RequestMapping(value = "forgetResetPassword.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> forgetResetPassword(String userName, String passwordNew, String forgetToken){
        return iUserService.forgetResetPassword(userName,passwordNew,forgetToken);
    }

    /**
     * 修改密码
     * @param passwordOld
     * @param passwordNew
     * @return
     */
    @RequestMapping(value = "resetPassword.do",method = RequestMethod.POST)
    @ResponseBody
    ServerResponse<String> resetPassword(HttpSession session, String passwordOld, String passwordNew){
        User user=(User) session.getAttribute(Const.CURRENT_USER);
        if (user==null)
            return ServerResponse.createByErrorMessage("用户未登陆");
        return iUserService.resetPassword(passwordOld,passwordNew,user);
    }


    /**
     * 更新用户信息
     * @param session
     * @param user
     * @return
     */
    @RequestMapping(value = "updateInformation.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<User> updateInformation(HttpSession session, User user){
        User currentUser=(User) session.getAttribute(Const.CURRENT_USER);
        if (currentUser==null)
            return ServerResponse.createByErrorMessage("用户未登陆");
        user.setUserId(currentUser.getUserId());
        ServerResponse<User> response=iUserService.updateInformation(user);
        if(response.isSuccess()){
            session.setAttribute(Const.CURRENT_USER,response.getData());
        }
        return response;

    }

    /**
     * 获取用户详细信息
     * @param session
     * @return
     */
    @RequestMapping(value = "getInformation.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<User> getInformation(HttpSession session) {
        User currentUser = (User) session.getAttribute(Const.CURRENT_USER);
        if (currentUser == null)
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"为登陆，需要强制登陆status=10");
        return iUserService.getInfomartion(currentUser.getUserId());
    }

    /**
     * 用户登陆  拿到session
     * @param userName
     * @param password
     * @param session
     * @return
     */
    @RequestMapping(value = "loginSession.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse  loginSession(String userName, String password, HttpSession session){
        ServerResponse<User> response= iUserService.login(userName,password);
        Map map = Maps.newHashMap();
        if (response.isSuccess()) {
            session.setAttribute(Const.CURRENT_USER,response.getData());
            map.put("user",response.getData());
            map.put("sessionId",session.getId());
        }
        return ServerResponse.createBySuccess(map);
    }

}
