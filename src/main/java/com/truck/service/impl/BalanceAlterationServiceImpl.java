package com.truck.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.truck.common.Const;
import com.truck.common.ServerResponse;
import com.truck.dao.BalanceAlterationMapper;
import com.truck.dao.OrderMapper;
import com.truck.dao.UserInfoMapper;
import com.truck.pojo.BalanceAlteration;
import com.truck.pojo.Order;
import com.truck.pojo.UserInfo;
import com.truck.service.IBalanceAlterationService;
import com.truck.util.DateTimeUtil;
import com.truck.vo.BalanceAlterationVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service("iBalanceAlterationService")
public class BalanceAlterationServiceImpl implements IBalanceAlterationService {

    @Autowired
    private BalanceAlterationMapper balanceAlterationMapper;
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private OrderMapper orderMapper;

    /**
     * 查询所有变动记录
     * @param pageNum
     * @param pageSize
     * @return
     */
    public ServerResponse balanceAlterationList(int pageNum, int pageSize, Integer status){
        PageHelper.startPage(pageNum, pageSize);
        List<BalanceAlteration> balanceAlterationList = balanceAlterationMapper.selectAllBalanceAlteration(status);
        if(balanceAlterationList.size() > 0){
            List<BalanceAlterationVo> balanceAlterationVoList = Lists.newArrayList();
            for(BalanceAlteration balanceAlterationItem : balanceAlterationList){
                BalanceAlterationVo balanceAlterationVo = this.assembleBalanceAlterationVo(balanceAlterationItem);
                balanceAlterationVoList.add(balanceAlterationVo);
            }
            PageInfo pageInfo = new PageInfo(balanceAlterationList);
            pageInfo.setList(balanceAlterationVoList);
            return ServerResponse.createBySuccess(pageInfo);
        }else{
            return ServerResponse.createByErrorMessage("当前没有记录");
        }
    }

    public ServerResponse getListByUserInfo(Integer userInfoId, int pageNum, int pageSize, Integer status){
        PageHelper.startPage(pageNum, pageSize);
        if(StringUtils.isEmpty(userInfoId)){
            return ServerResponse.createByErrorMessage("请选择要查询的用户信息");
        }
        List<BalanceAlteration> balanceAlterationList = balanceAlterationMapper.selectBalanceAlterationByUserInfo(userInfoId,status);
        List<BalanceAlterationVo> balanceAlterationVoList = Lists.newArrayList();
        if(balanceAlterationList.size() > 0){
            for(BalanceAlteration balanceAlterationItem : balanceAlterationList){
                BalanceAlterationVo balanceAlterationVo = this.assembleBalanceAlterationVo(balanceAlterationItem);
                balanceAlterationVoList.add(balanceAlterationVo);
            }
            PageInfo pageInfo = new PageInfo(balanceAlterationList);
            pageInfo.setList(balanceAlterationVoList);
            return ServerResponse.createBySuccess(pageInfo);
        }else{
            return ServerResponse.createByErrorMessage("当前用户下没有记录");
        }
    }


    public BalanceAlterationVo assembleBalanceAlterationVo(BalanceAlteration balanceAlteration){
        BalanceAlterationVo balanceAlterationVo = new BalanceAlterationVo();
        balanceAlterationVo.setBalanceAlterationId(balanceAlteration.getBalanceAlterationId());
        balanceAlterationVo.setUserInfoId(balanceAlteration.getUserInfoId());
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(balanceAlteration.getUserInfoId());
        balanceAlterationVo.setCompanyName(userInfo.getCompanyName());
        balanceAlterationVo.setAlterationMoney(balanceAlteration.getAlterationMoney());
        balanceAlterationVo.setAlterationStatus(balanceAlteration.getAlterationStatus());
        balanceAlterationVo.setStatusDesc(Const.AlterationStatusEnum.codeOf(balanceAlteration.getAlterationStatus()).getValue());
        balanceAlterationVo.setAlterationReason(balanceAlteration.getAlterationReason());
        balanceAlterationVo.setReasonDesc(Const.AlterationReasonEnum.codeOf(balanceAlteration.getAlterationReason()).getValue());
        balanceAlterationVo.setOrderId(balanceAlteration.getOrderId());
        if(!StringUtils.isEmpty(balanceAlteration.getOrderId())){
            Order order = orderMapper.selectByPrimaryKey(balanceAlteration.getOrderId());
            balanceAlterationVo.setOrderNo(order.getOrderNo());
        }
        balanceAlterationVo.setCreateTime(DateTimeUtil.dateToStr(balanceAlteration.getCreateTime()));
        balanceAlterationVo.setUpdateTime(DateTimeUtil.dateToStr(balanceAlteration.getUpdateTime()));
        return balanceAlterationVo;
    }
}
