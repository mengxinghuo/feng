package com.truck.dao;

import com.truck.pojo.Admin;
import com.truck.pojo.User;
import org.apache.ibatis.annotations.Param;

public interface AdminMapper {
    int deleteByPrimaryKey(Integer adminId);

    int insert(Admin record);

    int insertSelective(Admin record);

    Admin selectByPrimaryKey(Integer adminId);

    int updateByPrimaryKeySelective(Admin record);

    int updateByPrimaryKey(Admin record);


    int checkAdminName(String adminName);

    Admin selectLogin(@Param(value = "adminName") String adminName, @Param(value = "password") String password);

    int checkEmail(String email);

    String selectQuestionByAdminName(String adminName);

    int checkAnswer(@Param(value = "adminName")String adminName ,@Param(value = "question")String question,@Param(value = "answer")String answer);

    int updatePasswordByAdminName(@Param(value = "adminName")String adminName,@Param(value = "passwordNew")String passwordNew);

    int checkPassword(@Param(value = "passwordOld")String passwordOld,@Param(value = "adminId")Integer adminId);

    int checkEmailByAdminId(@Param(value = "email")String email,@Param(value = "adminId")Integer adminId);
}