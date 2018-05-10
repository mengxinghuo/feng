package com.truck.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.truck.common.Const;
import com.truck.common.ServerResponse;
import com.truck.dao.BalanceAlterationMapper;
import com.truck.dao.OrderMapper;
import com.truck.dao.UserInfoMapper;
import com.truck.dao.UserMapper;
import com.truck.pojo.BalanceAlteration;
import com.truck.pojo.Company;
import com.truck.pojo.Order;
import com.truck.pojo.UserInfo;
import com.truck.service.IBalanceAlterationService;
import com.truck.service.IUserInfoService;
import com.truck.util.BigDecimalUtil;
import com.truck.util.DateTimeUtil;
import com.truck.vo.BalanceAlterationVo;
import com.truck.vo.UserInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.List;

@Service("iUserInfoService")
public class UserInfoServiceImpl implements IUserInfoService {

    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private BalanceAlterationMapper balanceAlterationMapper;
    @Autowired
    private IBalanceAlterationService iBalanceAlterationService;

    /**
     * 新增用户信息
     * @param userId
     * @return
     */
    public ServerResponse addUserInfo(Integer userId , Company company, String dutyId, String officeAddress, String companyFax, String companyContactor){
        UserInfo checkUserInfo = userInfoMapper.selectCountByUserId(userId);
        if(checkUserInfo != null){
            return ServerResponse.createByErrorMessage("用户信息已存在，请进行修改");
        }
        //统计当前用户的累计采购额
        List<Order> orderList = orderMapper.selectPurchaseByUserId(userId);
        BigDecimal payment = new BigDecimal("0");
        if(orderList != null){
            for(Order orderItem : orderList){
                payment = BigDecimalUtil.add(payment.doubleValue(), orderItem.getPaymentPrice().doubleValue());
            }
        }
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(userId);
        userInfo.setCompanyName(company.getCompanyName());
        userInfo.setDutyId(dutyId);
        userInfo.setRegisterAddress(company.getCompanyAddress());
        userInfo.setOfficeAddress(officeAddress);
        userInfo.setCompanyPhone(company.getCompanyPhone());
        userInfo.setCompanyEmail(company.getCompanyEmail());
        userInfo.setCompanyFax(companyFax);
        userInfo.setCompanyContactor(companyContactor);
        userInfo.setCumulativePurchase(payment);
        userInfo.setMemberLevel("0");
        userInfo.setAccountBalance(new BigDecimal("0"));
        userInfo.setServiceBalance(new BigDecimal("0"));
        //进行新增
        int resultCount = userInfoMapper.insertSelective(userInfo);
        if(resultCount > 0){
            UserInfo searchUserInfo = userInfoMapper.selectByPrimaryKey(userInfo.getCustomerId());
            UserInfoVo userInfoVo = this.assembleUserInfoVo(searchUserInfo);
            return ServerResponse.createBySuccess("新增成功",userInfoVo);
        }
        return ServerResponse.createByErrorMessage("新增失败");
    }

    /**
     * 修改用户信息
     * @param userId
     * @return
     */
    public ServerResponse<String> updateUserInfo(Integer userId , Company company, String dutyId, String officeAddress, String companyFax, String companyContactor){
        UserInfo userInfo = userInfoMapper.selectCountByUserId(userId);
        if(userInfo == null){
            return ServerResponse.createByErrorMessage("用户信息不存在，请先进行新增");
        }
        //统计当前用户的累计采购额
        List<Order> orderList = orderMapper.selectPurchaseByUserId(userId);
        BigDecimal payment = new BigDecimal("0");
        if(orderList != null){
            for(Order orderItem : orderList){
                payment = BigDecimalUtil.add(payment.doubleValue(), orderItem.getPaymentPrice().doubleValue());
            }
        }
        if(StringUtils.isEmpty(company.getCompanyName())){
            userInfo.setCompanyName(company.getCompanyName());

        }
        if(StringUtils.isEmpty(dutyId)){
            userInfo.setDutyId(dutyId);

        }
        if(StringUtils.isEmpty(company.getCompanyAddress())){
            userInfo.setRegisterAddress(company.getCompanyAddress());

        }
        if(StringUtils.isEmpty(officeAddress)){
            userInfo.setOfficeAddress(officeAddress);

        }
        if(StringUtils.isEmpty(company.getCompanyPhone())){
            userInfo.setCompanyPhone(company.getCompanyPhone());

        }
        if(StringUtils.isEmpty(company.getCompanyEmail())){
            userInfo.setCompanyEmail(company.getCompanyEmail());

        }
        if(StringUtils.isEmpty(companyFax)){
            userInfo.setCompanyFax(companyFax);

        }
        if(StringUtils.isEmpty(companyContactor)){
            userInfo.setCompanyContactor(companyContactor);

        }
        if(StringUtils.isEmpty(payment)){
            userInfo.setCumulativePurchase(payment);

        }
        //进行修改
        int resultCount = userInfoMapper.updateByPrimaryKeySelective(userInfo);
        if(resultCount > 0){
            return ServerResponse.createBySuccessMessage("修改成功");
        }
        return ServerResponse.createByErrorMessage("修改失败");
    }

    /**
     * 查询用户信息
     * @param userId
     * @return
     */
    public ServerResponse<UserInfoVo> getUserInfo(Integer userId){
        UserInfo checkUser = userInfoMapper.selectCountByUserId(userId);
        if(checkUser == null){
            return ServerResponse.createByErrorMessage("用户信息不存在");
        }
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(checkUser.getCustomerId());
        UserInfoVo userInfoVo = this.assembleUserInfoVo(userInfo);
        return ServerResponse.createBySuccess(userInfoVo);
    }

    /**
     * 查询所有用户信息，需要管理员权限(可根据公司名进行模糊查询)
     * @param pageNum
     * @param pageSize
     * @return
     */
    public ServerResponse<PageInfo> getAllUserInfo(String companyName, int pageNum, int pageSize){
        PageHelper.startPage(pageNum, pageSize);
        if (!StringUtils.isEmpty(companyName)) {
            companyName = new StringBuilder().append("%").append(companyName).append("%").toString();
        }
        List<UserInfo> userInfoList = userInfoMapper.selectAllUserInfo(companyName);
        List<UserInfoVo> userInfoVoList = Lists.newArrayList();
        for(UserInfo userInfoItem : userInfoList){
            UserInfoVo userInfoVo = assembleUserInfoVo(userInfoItem);
            userInfoVoList.add(userInfoVo);
        }
        PageInfo pageInfo = new PageInfo(userInfoList);
        pageInfo.setList(userInfoVoList);
        return ServerResponse.createBySuccess(pageInfo);
    }

    /**
     * 根据用户Id查询用户信息，管理员权限
     * @param userId
     * @return
     */
    public ServerResponse<UserInfoVo> searchByUserId(Integer userId){
        UserInfo userInfo = userInfoMapper.selectCountByUserId(userId);
        if(userInfo == null){
            return ServerResponse.createByErrorMessage("该用户信息不存在");
        }
        UserInfoVo userInfoVo = assembleUserInfoVo(userInfo);
        return ServerResponse.createBySuccess(userInfoVo);
    }

    /**
     * 充值账户金额
     * @param userInfoId
     * @param money
     * @return
     */
    public ServerResponse rechargeBalance(Integer userInfoId, BigDecimal money){
        if(StringUtils.isEmpty(userInfoId)){
            return ServerResponse.createByErrorMessage("请选择要充值的用户");
        }
        UserInfo search = userInfoMapper.selectByPrimaryKey(userInfoId);
        if(search == null){
            return ServerResponse.createByErrorMessage("该用户信息不存在");
        }
        if(StringUtils.isEmpty(money)){
            return ServerResponse.createByErrorMessage("请输入要充值的金额");
        }
        BalanceAlteration balanceAlteration = new BalanceAlteration();
        balanceAlteration.setUserInfoId(userInfoId);
        balanceAlteration.setAlterationMoney(money);
        balanceAlteration.setAlterationStatus(Const.AlterationStatusEnum.INCOME.getCode());
        balanceAlteration.setAlterationReason(Const.AlterationReasonEnum.RECHARGE.getCode());
        int resultCount = balanceAlterationMapper.insertSelective(balanceAlteration);
        if(resultCount > 0){
            UserInfo userInfo = new UserInfo();
            userInfo.setCustomerId(userInfoId);
            userInfo.setAccountBalance(BigDecimalUtil.add(search.getAccountBalance().doubleValue(),money.doubleValue()));
            resultCount = userInfoMapper.updateByPrimaryKeySelective(userInfo);
            if(resultCount > 0){
                userInfo = userInfoMapper.selectByPrimaryKey(userInfoId);
                UserInfoVo userInfoVo = this.assembleUserInfoVo(userInfo);
                return ServerResponse.createBySuccess("充值成功",userInfoVo);
            }
            return ServerResponse.createByErrorMessage("充值失败");
        }else{
            return ServerResponse.createByErrorMessage("充值异常");
        }
    }

    public ServerResponse getBalanceAlteration(Integer userId,int pageNum,int pageSize){
        PageHelper.startPage(pageNum, pageSize);
        UserInfo userInfo = userInfoMapper.selectCountByUserId(userId);
        if(userInfo == null){
            return ServerResponse.createByErrorMessage("本用户的用户信息为空");
        }
        List<BalanceAlteration> balanceAlterationList = balanceAlterationMapper.selectByUserInfo(userInfo.getCustomerId());
        if(balanceAlterationList.size() > 0){
            List<BalanceAlterationVo> balanceAlterationVoList = Lists.newArrayList();
            for(BalanceAlteration balanceAlterationItem : balanceAlterationList){
                BalanceAlterationVo balanceAlterationVo = iBalanceAlterationService.assembleBalanceAlterationVo(balanceAlterationItem);
                balanceAlterationVoList.add(balanceAlterationVo);
            }
            PageInfo pageInfo = new PageInfo(balanceAlterationList);
            pageInfo.setList(balanceAlterationVoList);
            return ServerResponse.createBySuccess(pageInfo);
        }else{
            return ServerResponse.createByErrorMessage("当前用户下没有记录");
        }

    }





    /**
     * 时间提取转型重新封装
     * @param userInfo
     * @return
     */
    private UserInfoVo assembleUserInfoVo(UserInfo userInfo){
        UserInfoVo userInfoVo = new UserInfoVo();
        userInfoVo.setCustomerId(userInfo.getCustomerId());
        userInfoVo.setUserId(userInfo.getUserId());
        userInfoVo.setCompanyName(userInfo.getCompanyName());
        userInfoVo.setDutyId(userInfo.getDutyId());
        userInfoVo.setCumulativePurchase(userInfo.getCumulativePurchase());
        userInfoVo.setRegisterAddress(userInfo.getRegisterAddress());
        userInfoVo.setOfficeAddress(userInfo.getOfficeAddress());
        userInfoVo.setCompanyPhone(userInfo.getCompanyPhone());
        userInfoVo.setCompanyEmail(userInfo.getCompanyEmail());
        userInfoVo.setCompanyFax(userInfo.getCompanyFax());
        userInfoVo.setCompanyContactor(userInfo.getCompanyContactor());
        userInfoVo.setMemberLevel(userInfo.getMemberLevel());
        userInfoVo.setAccountBalance(userInfo.getAccountBalance());
        userInfoVo.setServiceBalance(userInfo.getServiceBalance());
        userInfoVo.setCreateTime(DateTimeUtil.dateToStr(userInfo.getCreateTime()));
        userInfoVo.setUpdateTime(DateTimeUtil.dateToStr(userInfo.getUpdateTime()));
        return userInfoVo;
    }

}
