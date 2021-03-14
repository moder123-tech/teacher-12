package cn.com.teacher.dao;

import cn.com.teacher.bean.UserInformation;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
public interface UserDao {

    /**
     *
     * 用户注册 @param userInformation
     * 是否成功 @return
     */
    int insertUser(UserInformation userInformation);

    /**
     *
     * 邮箱 @param email
     * 密码 @param password
     * 是否成功 @return
     */
    int getUser(@Param("u_number") String u_number, @Param("u_password") String u_password);

}
