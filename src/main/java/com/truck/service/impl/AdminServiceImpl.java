package com.truck.service.impl;

import com.truck.common.Const;
import com.truck.common.ServerResponse;
import com.truck.common.TokenCache;
import com.truck.dao.AdminMapper;
import com.truck.dao.ShopMapper;
import com.truck.dao.UserMapper;
import com.truck.pojo.Admin;
import com.truck.pojo.Company;
import com.truck.pojo.Shop;
import com.truck.pojo.User;
import com.truck.service.IAdminService;
import com.truck.util.MD5Util;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service("iAdminService")
public class AdminServiceImpl implements IAdminService {

    @Autowired
    AdminMapper adminMapper;
    @Autowired
    ShopMapper shopMapper;

    @Override
    public ServerResponse<Admin> login(String adminName, String password) {
        int resultCount=adminMapper.checkAdminName(adminName);
        if (resultCount==0)
            return ServerResponse.createByErrorMessage("用户名不存在");
        String md5Password= MD5Util.MD5EncodeUtf8(password);
        Admin admin=adminMapper.selectLogin(adminName,md5Password);
        if (admin==null){
            return ServerResponse.createByErrorMessage("密码错误");
        }
             admin.setPassword(StringUtils.EMPTY);
        if (this.checkAdminRole(admin).isSuccess()) {
            return ServerResponse.createBySuccess("登陆成功", admin);
        }
        Shop shop = shopMapper.selectByAdminId(admin.getAdminId());
        if(shop ==null){
            return ServerResponse.createBySuccessNeedData("没有创建公司", admin);
        }
        if (StringUtils.isBlank(shop.getShopProxyCertificate()) ||
                StringUtils.isBlank(shop.getShopAccount()) ||
                StringUtils.isBlank(shop.getShopSppkp()) ||
                StringUtils.isBlank(shop.getShopTaxCard()) ||
                StringUtils.isBlank(shop.getShopLicence())) {

            return ServerResponse.createBySuccessNeedData("公司文件资料不完整", admin);
        }
        if( shop.getShopStatus().equals(Const.ShopStatusEnum.REVIEWING.getCode())){
            return ServerResponse.createBySuccessNeedData("资料审核中，请等待", admin);
        }
        if( shop.getShopStatus().equals(Const.ShopStatusEnum.REVIEW_FAILED.getCode())){
            return ServerResponse.createBySuccessNeedData("资料审核不通过", admin);
        }
        return ServerResponse.createBySuccess("登陆成功", admin);
    }

    @Override
    public ServerResponse<String> register(Admin admin) {
        ServerResponse validResponse=this.checkValid(admin.getAdminName(), Const.ADMINNAME);
        if (!validResponse.isSuccess())
            return validResponse;


        validResponse=this.checkValid(admin.getEmail(),Const.EMAIL);
        if (!validResponse.isSuccess())
            return validResponse;

        admin.setRole(Const.AdminRole.ADMINROLE_ADMIN);

        //md5加密
        admin.setPassword(MD5Util.MD5EncodeUtf8(admin.getPassword()));

        int resultCount=adminMapper.insert(admin);
        if(resultCount==0)
            return ServerResponse.createByErrorMessage("注册失败");
        return ServerResponse.createBySuccess("注册成功");
    }

    @Override
    public ServerResponse<String> checkValid(String str, String type) {
        if (StringUtils.isNotBlank(type)){
            //开始校验
            if (Const.ADMINNAME.equals(type)){
                int resultCount=adminMapper.checkAdminName(str);
                if (resultCount>0)
                    return ServerResponse.createByErrorMessage("用户名已存在");
            }

            if (Const.EMAIL.equals(type)){
                int resultCount=adminMapper.checkEmail(str);
                if (resultCount>0)
                    return ServerResponse.createByErrorMessage("email已存在");
            }

        }else {
            return ServerResponse.createByErrorMessage("参数错误");

        }
        return ServerResponse.createBySuccessMessage("校验成功");
    }

    @Override
    public ServerResponse<String> selectQuestion(String adminName) {
        ServerResponse<String> validResponse =this.checkValid(adminName,Const.ADMINNAME);
        if(validResponse.isSuccess())
            return ServerResponse.createByErrorMessage("用户名不存在");
        String question=adminMapper.selectQuestionByAdminName(adminName);
        if (StringUtils.isNotBlank(question))
            return ServerResponse.createBySuccess(question);
        return ServerResponse.createByErrorMessage("找回密码的问题是空");
    }

    @Override
    public ServerResponse<String> checkAnswer(String adminName, String question, String answer) {
        int resultCount =adminMapper.checkAnswer(adminName,question,answer);
        if (resultCount>0) {
            String forgetToken= UUID.randomUUID().toString();
            TokenCache.setKey(TokenCache.TOKEN_PREFIX+adminName,forgetToken);
            return ServerResponse.createBySuccess(forgetToken);

        }
        return ServerResponse.createByErrorMessage("问题答案错误");
    }

    @Override
    public ServerResponse<String> forgetResetPassword(String adminName, String passwordNew, String forgetToken) {
        if (StringUtils.isBlank(forgetToken))
            return ServerResponse.createByErrorMessage("参数错误，token需要传递");
        ServerResponse validResponse=this.checkValid(adminName,Const.ADMINNAME);
        if(validResponse.isSuccess())
            return ServerResponse.createByErrorMessage("用户名不存在");
        String token=TokenCache.getKey(TokenCache.TOKEN_PREFIX+adminName);
        if (StringUtils.isBlank(token))
            return ServerResponse.createByErrorMessage("token无效或者过期");

        if (StringUtils.equals(forgetToken,token)){
            String md5Password=MD5Util.MD5EncodeUtf8(passwordNew);
            int rowCount=adminMapper.updatePasswordByAdminName(adminName,md5Password);
            if (rowCount>0)
                return ServerResponse.createBySuccessMessage("修改密码成功");
        }else {
            return ServerResponse.createByErrorMessage("token错误，请重新获取重置密码的token");
        }
        return ServerResponse.createByErrorMessage("修改密码失败");
    }

    @Override
    public ServerResponse<String> resetPassword(String passwordOld, String passwordNew, Admin admin) {
        String md5Password=MD5Util.MD5EncodeUtf8(passwordOld);
        int resultCOunt=adminMapper.checkPassword(md5Password,admin.getAdminId());
        if (resultCOunt==0)
            return ServerResponse.createByErrorMessage("密码错误");
        admin.setPassword(MD5Util.MD5EncodeUtf8(passwordNew));
        int updateCount=adminMapper.updateByPrimaryKeySelective(admin);
        if (updateCount>0)
            return ServerResponse.createBySuccessMessage("修改密码成功");

        return ServerResponse.createByErrorMessage("修改密码失败");
    }

    @Override
    public ServerResponse<Admin> updateInformation(Admin admin) {
        //ADMINNAME不能被跟新
        //email也要进行一个校验，校验新的email是不是已经存在，并且存在的email如果相同的话，不能是我们当前的这个用户的
        int resultCount=adminMapper.checkEmailByAdminId(admin.getEmail(),admin.getAdminId());
        if (resultCount>0)
            return  ServerResponse.createByErrorMessage("email已存在，请更换email再尝试更新");
        Admin updateAdmin=new Admin();
        updateAdmin.setAdminId(admin.getAdminId());
        updateAdmin.setEmail(admin.getEmail());
        updateAdmin.setPhone(admin.getPhone());
        updateAdmin.setQuestion(admin.getQuestion());
        updateAdmin.setAnswer(admin.getAnswer());
        int updateCount=adminMapper.updateByPrimaryKeySelective(updateAdmin);
        if (updateCount>0)
            return ServerResponse.createBySuccess("更新成功",updateAdmin);
        return ServerResponse.createByErrorMessage("更新失败");
    }

    @Override
    public ServerResponse<Admin> getInfomartion(Integer userid) {
        Admin admin=adminMapper.selectByPrimaryKey(userid);
        if (admin==null)
            return ServerResponse.createByErrorMessage("找不到当前用户");
        admin.setPassword(StringUtils.EMPTY);
        return ServerResponse.createBySuccess(admin);
    }


    //backend

    /**
     * 校验是否是超级管理员
     * @param admin
     * @return
     */
    public ServerResponse checkAdminRole(Admin admin){
        if (admin!=null && admin.getRole().intValue()==Const.AdminRole.ADMINROLE_SUPERADMIN)

            return ServerResponse.createBySuccess();
        System.out.println(admin.getRole());
        return ServerResponse.createByError();
    }

    /**
     * 校验店铺状态
     * @param admin
     * @return
     */
    public ServerResponse checkShopStatus(Admin admin){
        if (admin!=null ){
            Shop shop = shopMapper.selectByAdminId(admin.getAdminId());
            if (shop != null) {
                if(Const.ShopStatusEnum.REVIEW_SUCCESS.getCode() == shop.getShopStatus()){
                    return ServerResponse.createBySuccess();
                }else if(Const.ShopStatusEnum.REVIEWING.getCode() == shop.getShopStatus()){
                    return ServerResponse.createByErrorMessage("店铺"+shop.getShopName()+"工商信息正在审核中");
                }else if(Const.ShopStatusEnum.REVIEW_FAILED.getCode() == shop.getShopStatus()){
                    return ServerResponse.createByErrorMessage("店铺"+shop.getShopName()+"工商信息审核不通过");
                }
            }
            return ServerResponse.createByErrorMessage("店铺不存在");
        }
        return ServerResponse.createByError();
    }
}
