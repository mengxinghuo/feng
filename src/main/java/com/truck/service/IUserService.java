package com.truck.service;

import com.truck.common.ServerResponse;
import com.truck.pojo.Admin;
import com.truck.pojo.User;
import com.truck.common.ServerResponse;
import com.truck.pojo.User;

public interface IUserService {
    ServerResponse<User> login(String userName, String password);

    ServerResponse<String> register(User user);

    ServerResponse<String> checkValid(String str, String type);

    ServerResponse<String> selectQuestion(String userName);

    ServerResponse<String> checkAnswer(String userName, String question, String answer);

    ServerResponse<String> forgetResetPassword(String userName, String passwordNew, String forgetToken);

    ServerResponse<String> resetPassword(String passwordOld, String passwordNew, User user);

    ServerResponse<User> updateInformation(User user);

    ServerResponse<User> getInfomartion(Integer userid);

    ServerResponse checkUserRole(User user);

}
