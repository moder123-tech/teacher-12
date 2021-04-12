package cn.com.teacher.dao;

import cn.com.teacher.bean.UserInformation;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author wx
 */
@Mapper
public interface UserDao {

    /**
     * 用户注册 @param userInformation
     * 是否成功 @return
     */
    int insertUser(UserInformation userInformation);

    /**
     * 邮箱 @param email
     * 密码 @param password
     * 是否成功 @return
     */
    UserInformation getUser(@Param("u_number") String u_number, @Param("u_password") String u_password);


    /**
     * 传入要更新的用户对象@param userInformation
     * 返回是否更新成功@return
     */
    int updateUpdateUploadImage(UserInformation userInformation);

    /**
     * 传入要查询的对象账号@param u_number
     * 返回对象的头像路径@return
     */
    String getUserImage(@Param("u_number") String u_number);

}
