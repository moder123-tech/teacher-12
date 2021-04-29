package cn.com.teacher.service;

import cn.com.teacher.bean.UserInformation;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * @author wx
 */
public interface UserService {

    /**
     * 添加用户 @param userInformation
     * 是否成功 @return
     */
    int insertUser(UserInformation userInformation);

    /**
     * 邮箱 @param email
     * 是否成功 @return
     */
    UserInformation getUser(@Param("u_number") String u_number);

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

    /**
     * 传入用户所修改的昵称@param u_name
     * 传入用户所修改的密码@param u_password
     * 获取用户的邮箱账号@param u_number
     * 返回修改是否成功@return
     */
    int updateUserInformation(@Param("u_name") String u_name, @Param("u_password") String u_password, @Param("u_number") String u_number);

    /**
     * 传入查询用户表要去除的用户@param u_number
     *
     * @return
     */
    List<UserInformation> selectAllUsers(@Param("u_number") String u_number);

    /**
     * 传入要删除的用户账号@param u_number
     * 返回是否删除成功@return
     */
    int deleteUser(@Param("u_number") String u_number);

    /**
     * 传入要找回密码的账号@param userInformation
     * 返回是否更新密码成功@return
     */
    int updateUser(UserInformation userInformation);

    /**
     * 传入要更新用户的邮箱账号@param u_number
     * 传入要更新用户的密码@param u_password
     * 传入要更新用户的权限@param u_state
     * 返回更新用户是否成功@return
     */
    int updateUsers(@Param("u_number") String u_number, @Param("u_password") String u_password, @Param("u_state") String u_state);

    /**
     * 传入用户的邮箱账号@param u_number
     * 传入用户登录时间@param u_time
     * 返回更新用户是否成功@return
     */
    void updateUserTime(@Param("u_number") String u_number, @Param("u_time") String u_time);
}
