package com.truck.service.impl;

import com.google.common.collect.Lists;
import com.truck.common.Const;
import com.truck.common.ServerResponse;
import com.truck.common.TokenCache;
import com.truck.dao.CompanyMapper;
import com.truck.dao.UserMapper;
import com.truck.pojo.Company;
import com.truck.pojo.User;
import com.truck.service.IUserService;
import com.truck.util.MD5Util;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service("iUserService")
public class UserServiceImpl implements IUserService {

    @Autowired
    UserMapper userMapper;
    @Autowired
    CompanyMapper companyMapper;


    @Override
    public ServerResponse<User> login(String userName, String password) {
        int resultCount = userMapper.checkUserName(userName);
        if (resultCount==0)
            return ServerResponse.createByErrorMessage("用户名不存在");
        String md5Password= MD5Util.MD5EncodeUtf8(password);
        User user=userMapper.selectLogin(userName,md5Password);
        if (user==null) {
            return ServerResponse.createByErrorMessage("密码错误");
        }
        user.setPassword(StringUtils.EMPTY);
        if (this.checkUserRole(user).isSuccess()) {
            return ServerResponse.createBySuccess("登陆成功", user);
        }
        Company company = companyMapper.selectByUserId(user.getUserId());
        if(company ==null){
            return ServerResponse.createBySuccessNeedData("没有创建公司", user);
        }
        if (StringUtils.isBlank(company.getCompanyAccount()) ||
                StringUtils.isBlank(company.getCompanySppkp()) ||
                StringUtils.isBlank(company.getCompanyTaxCard()) ||
                StringUtils.isBlank(company.getCompanyLicence())) {

            return ServerResponse.createBySuccessNeedData("公司文件资料不完整", user);
        }
        return ServerResponse.createBySuccess("登陆成功", user);
    }

    @Override
    public ServerResponse<String> register(User user) {
        ServerResponse validResponse=this.checkValid(user.getUserName(), Const.USERNAME);
        if (!validResponse.isSuccess()){
            if(Const.DEVICE_SYSTEM_USER_PHONE.equals("88888888")){
                return ServerResponse.createBySuccess("注册成功");
            }else{
                return validResponse;
            }
        }
        validResponse=this.checkValid(user.getEmail(),Const.EMAIL);
        if (!validResponse.isSuccess())
            return validResponse;

        user.setRole(Const.Role.ROLE_COSTOMER);

        //md5加密
        user.setPassword(MD5Util.MD5EncodeUtf8(user.getPassword()));

        int resultCount=userMapper.insert(user);
        if(resultCount==0)
            return ServerResponse.createByErrorMessage("注册失败");
        return ServerResponse.createBySuccess("注册成功");
    }

    @Override
    public ServerResponse<String> checkValid(String str, String type) {
        if (StringUtils.isNotBlank(type)){
            //开始校验
            if (Const.USERNAME.equals(type)){
                int resultCount=userMapper.checkUserName(str);
                if (resultCount>0)
                    return ServerResponse.createByErrorMessage("用户名已存在");
            }

            if (Const.EMAIL.equals(type)){
                int resultCount=userMapper.checkEmail(str);
                if (resultCount>0)
                    return ServerResponse.createByErrorMessage("email已存在");
            }

        }else {
            return ServerResponse.createByErrorMessage("参数错误");

        }
        return ServerResponse.createBySuccessMessage("校验成功");
    }

    @Override
    public ServerResponse<String> selectQuestion(String userName) {
        ServerResponse<String> validResponse =this.checkValid(userName,Const.USERNAME);
        if(validResponse.isSuccess())
            return ServerResponse.createByErrorMessage("用户名不存在");
        String question=userMapper.selectQuestionByUsername(userName);
        if (StringUtils.isNotBlank(question))
            return ServerResponse.createBySuccess(question);
        return ServerResponse.createByErrorMessage("找回密码的问题是空");
    }

    @Override
    public ServerResponse<String> checkAnswer(String userName, String question, String answer) {
        int resultCount =userMapper.checkAnswer(userName,question,answer);
        if (resultCount>0) {
            String forgetToken= UUID.randomUUID().toString();
            TokenCache.setKey(TokenCache.TOKEN_PREFIX+userName,forgetToken);
            return ServerResponse.createBySuccess(forgetToken);

        }
        return ServerResponse.createByErrorMessage("问题答案错误");
    }

    @Override
    public ServerResponse<String> forgetResetPassword(String userName, String passwordNew, String forgetToken) {
        if (StringUtils.isBlank(forgetToken))
            return ServerResponse.createByErrorMessage("参数错误，token需要传递");
        ServerResponse validResponse=this.checkValid(userName,Const.USERNAME);
        if(validResponse.isSuccess())
            return ServerResponse.createByErrorMessage("用户名不存在");
        String token=TokenCache.getKey(TokenCache.TOKEN_PREFIX+userName);
        if (StringUtils.isBlank(token))
            return ServerResponse.createByErrorMessage("token无效或者过期");

        if (StringUtils.equals(forgetToken,token)){
            String md5Password=MD5Util.MD5EncodeUtf8(passwordNew);
            int rowCount=userMapper.updatePasswordByUsername(userName,md5Password);
            if (rowCount>0)
                return ServerResponse.createBySuccessMessage("修改密码成功");
        }else {
            return ServerResponse.createByErrorMessage("token错误，请重新获取重置密码的token");
        }
        return ServerResponse.createByErrorMessage("修改密码失败");
    }

    @Override
    public ServerResponse<String> resetPassword(String passwordOld, String passwordNew, User user) {
        String md5Password=MD5Util.MD5EncodeUtf8(passwordOld);
        int resultCOunt=userMapper.checkPassword(md5Password,user.getUserId());
        if (resultCOunt==0)
            return ServerResponse.createByErrorMessage("密码错误");
        user.setPassword(MD5Util.MD5EncodeUtf8(passwordNew));
        int updateCount=userMapper.updateByPrimaryKeySelective(user);
        if (updateCount>0)
            return ServerResponse.createBySuccessMessage("修改密码成功");

        return ServerResponse.createByErrorMessage("修改密码失败");
    }

    @Override
    public ServerResponse<User> updateInformation(User user) {
        //userName不能被跟新
        //email也要进行一个校验，校验新的email是不是已经存在，并且存在的email如果相同的话，不能是我们当前的这个用户的
        int resultCount=userMapper.checkEmailByUserId(user.getEmail(),user.getUserId());
        if (resultCount>0)
            return  ServerResponse.createByErrorMessage("email已存在，请更换email再尝试更新");
        User updateUser=new User();
        updateUser.setUserId(user.getUserId());
        updateUser.setEmail(user.getEmail());
        updateUser.setPhone(user.getPhone());
        updateUser.setQuestion(user.getQuestion());
        updateUser.setAnswer(user.getAnswer());
        int updateCount=userMapper.updateByPrimaryKeySelective(updateUser);
        if (updateCount>0)
            return ServerResponse.createBySuccess("更新成功",updateUser);
        return ServerResponse.createByErrorMessage("更新失败");
    }

    @Override
    public ServerResponse<User> getInfomartion(Integer userid) {
        User user=userMapper.selectByPrimaryKey(userid);
        if (user==null)
            return ServerResponse.createByErrorMessage("找不到当前用户");
        user.setPassword(StringUtils.EMPTY);
        return ServerResponse.createBySuccess(user);
    }


    //backend

    /**
     * 校验是否是买方公司的管理员
     * @param user
     * @return
     */
    public ServerResponse checkUserRole(User user){
        if (user!=null && user.getRole().intValue()==Const.Role.ROLE_ADMIN)
            return ServerResponse.createBySuccess();
        return ServerResponse.createByError();
    }
}
