package com.truck.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.truck.common.ServerResponse;
import com.truck.dao.AdminBankMapper;
import com.truck.pojo.AdminBank;
import com.truck.service.IAdminBankService;
import com.truck.util.DateTimeUtil;
import com.truck.vo.AdminBankListVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("IAdminAdminBankService")
public class AdminBankServiceImpl implements IAdminBankService {

    @Autowired
    private AdminBankMapper adminBankMapper;

    public ServerResponse add(Integer adminId, AdminBank adminBank){
        adminBank.setAdminId(adminId);
        int rowCount = adminBankMapper.insertSelective(adminBank);
        if(rowCount > 0){
            Map result = Maps.newHashMap();
            result.put("adminBankId",adminBank.getBankId());
            return ServerResponse.createBySuccess("新建账号成功",result);
        }
        return ServerResponse.createByErrorMessage("新建账号失败");
    }

    public ServerResponse<String> del(Integer adminId, Integer adminBankId){
        int rowCount = adminBankMapper.deleteByBankIdAdminId(adminId, adminBankId);
        if(rowCount > 0){
            return ServerResponse.createBySuccess("删除账号成功");
        }
        return ServerResponse.createByErrorMessage("删除账号失败");
    }

    public ServerResponse update(Integer adminId, AdminBank adminBank){
        adminBank.setAdminId(adminId);
        int rowCount = adminBankMapper.updateByBank(adminBank);
        if(rowCount > 0){
            return ServerResponse.createBySuccess("更新账号成功");
        }
        return ServerResponse.createByErrorMessage("更新账号失败");
    }

    public ServerResponse<AdminBankListVo> select(Integer adminId, Integer adminBankId){
        AdminBank adminBank = adminBankMapper.selectByBankIdAdminId(adminId, adminBankId);
        if(adminBank == null){
            return ServerResponse.createByErrorMessage("无法查询到该地址");
        }
        AdminBankListVo adminBankListVo = assembleAdminBankListVo(adminBank);
        return ServerResponse.createBySuccess("查询成功",adminBankListVo);
    }

    public ServerResponse<PageInfo> list(Integer adminId, int pageNum, int pageSize){
        PageHelper.startPage(pageNum, pageSize);
        List<AdminBank> adminBankList = adminBankMapper.selectByAdminId(adminId);
        List<AdminBankListVo> adminBankListVoList = Lists.newArrayList();
        for(AdminBank adminBankItem : adminBankList){
            AdminBankListVo adminBankListVo = assembleAdminBankListVo(adminBankItem);
            adminBankListVoList.add(adminBankListVo);
        }
        PageInfo pageInfo = new PageInfo(adminBankList);
        pageInfo.setList(adminBankListVoList);
        return ServerResponse.createBySuccess(pageInfo);
    }

    private AdminBankListVo assembleAdminBankListVo(AdminBank adminBank){
        AdminBankListVo adminBankListVo = new AdminBankListVo();
        adminBankListVo.setBankId(adminBank.getBankId());
        adminBankListVo.setAdminId(adminBank.getAdminId());
        adminBankListVo.setBankName(adminBank.getBankName());
        adminBankListVo.setBankAddress(adminBank.getBankAddress());
        adminBankListVo.setBankAccount(adminBank.getBankAccount());
        adminBankListVo.setBankUserName(adminBank.getBankUserName());
        adminBankListVo.setContactNumber(adminBank.getContactNumber());
        adminBankListVo.setPresentAddress(adminBank.getPresentAddress());
        adminBankListVo.setCreateTime(DateTimeUtil.dateToStr(adminBank.getCreateTime()));
        adminBankListVo.setUpdateTime(DateTimeUtil.dateToStr(adminBank.getUpdateTime()));
        adminBankListVo.setSwiftCode(adminBank.getSwiftCode());
        adminBankListVo.setCurrency(adminBank.getCurrency());
        return adminBankListVo;
    }
}
