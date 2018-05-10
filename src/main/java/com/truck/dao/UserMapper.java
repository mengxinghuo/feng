package com.truck.dao;

import com.truck.pojo.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    int checkUserName(String userName);

    User selectLogin(@Param(value = "username") String userName, @Param(value = "password") String password);

    int checkEmail(String email);

    String selectQuestionByUsername(String userName);

    int checkAnswer(@Param(value = "username")String userName ,@Param(value = "question")String question,@Param(value = "answer")String answer);

    int updatePasswordByUsername(@Param(value = "username")String userName,@Param(value = "passwordNew")String passwordNew);

    int checkPassword(@Param(value = "passwordOld")String passwordOld,@Param(value = "id")Integer id);

    int checkEmailByUserId(@Param(value = "email")String email,@Param(value = "userid")Integer userid);
}