package com.truck.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.truck.common.ServerResponse;
import com.truck.dao.BankMapper;
import com.truck.pojo.Bank;
import com.truck.service.IBankService;
import com.truck.util.DateTimeUtil;
import com.truck.vo.BankListVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("iBankService")
public class BankServiceImpl implements IBankService {

    @Autowired
    private BankMapper bankMapper;

    public ServerResponse add(Integer userId, Bank bank){
        bank.setUserId(userId);
        int rowCount = bankMapper.insertSelective(bank);
        if(rowCount > 0){
            Map result = Maps.newHashMap();
            result.put("bankId",bank.getBankId());
            return ServerResponse.createBySuccess("新建账号成功",result);
        }
        return ServerResponse.createByErrorMessage("新建账号失败");
    }

    public ServerResponse<String> del(Integer userId, Integer bankId){
        int rowCount = bankMapper.deleteByBankIdUserId(userId, bankId);
        if(rowCount > 0){
            return ServerResponse.createBySuccess("删除账号成功");
        }
        return ServerResponse.createByErrorMessage("删除账号失败");
    }

    public ServerResponse update(Integer userId, Bank bank){
        bank.setUserId(userId);
        int rowCount = bankMapper.updateByBank(bank);
        if(rowCount > 0){
            return ServerResponse.createBySuccess("更新账号成功");
        }
        return ServerResponse.createByErrorMessage("更新账号失败");
    }

    public ServerResponse<BankListVo> select(Integer userId, Integer bankId){
        Bank bank = bankMapper.selectByBankIdUserId(userId, bankId);
        if(bank == null){
            return ServerResponse.createByErrorMessage("无法查询到该地址");
        }
        BankListVo bankListVo = assembleBankListVo(bank);
        return ServerResponse.createBySuccess("查询成功",bankListVo);
    }

    public ServerResponse<PageInfo> list(Integer userId, int pageNum, int pageSize){
        PageHelper.startPage(pageNum, pageSize);
        List<Bank> bankList = bankMapper.selectByUserId(userId);
        List<BankListVo> bankListVoList = Lists.newArrayList();
        for(Bank bankItem : bankList){
            BankListVo bankListVo = assembleBankListVo(bankItem);
            bankListVoList.add(bankListVo);
        }
        PageInfo pageInfo = new PageInfo(bankList);
        pageInfo.setList(bankListVoList);
        return ServerResponse.createBySuccess(pageInfo);
    }

    private BankListVo assembleBankListVo(Bank bank){
        BankListVo bankListVo = new BankListVo();
        bankListVo.setBankId(bank.getBankId());
        bankListVo.setUserId(bank.getUserId());
        bankListVo.setBankName(bank.getBankName());
        bankListVo.setBankAddress(bank.getBankAddress());
        bankListVo.setBankAccount(bank.getBankAccount());
        bankListVo.setBankUserName(bank.getBankUserName());
        bankListVo.setContactNumber(bank.getContactNumber());
        bankListVo.setPresentAddress(bank.getPresentAddress());
        bankListVo.setCreateTime(DateTimeUtil.dateToStr(bank.getCreateTime()));
        bankListVo.setUpdateTime(DateTimeUtil.dateToStr(bank.getUpdateTime()));
        bankListVo.setSwiftCode(bank.getSwiftCode());
        bankListVo.setCurrency(bank.getCurrency());
        return bankListVo;
    }
}
