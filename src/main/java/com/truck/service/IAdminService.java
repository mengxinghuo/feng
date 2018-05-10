package com.truck.service;

import com.truck.common.ServerResponse;
import com.truck.pojo.Admin;


public interface IAdminService {
    ServerResponse<Admin> login(String adminName, String password);

    ServerResponse<String> register(Admin admin);

    ServerResponse<String> checkValid(String str, String type);

    ServerResponse<String> selectQuestion(String adminName);

    ServerResponse<String> checkAnswer(String adminName, String question, String answer);

    ServerResponse<String> forgetResetPassword(String adminName, String passwordNew, String forgetToken);

    ServerResponse<String> resetPassword(String passwordOld, String passwordNew, Admin admin);

    ServerResponse<Admin> updateInformation(Admin admin);

    ServerResponse<Admin> getInfomartion(Integer adminId);

    ServerResponse checkAdminRole(Admin admin);

    ServerResponse checkShopStatus(Admin admin);
}
