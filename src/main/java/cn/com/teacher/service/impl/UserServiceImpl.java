package cn.com.teacher.service.impl;

import cn.com.teacher.bean.UserInformation;
import cn.com.teacher.dao.UserDao;
import cn.com.teacher.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @author wx
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    public UserDao userDao;

    @Override
    public int insertUser(UserInformation userInformation) {
        return userDao.insertUser(userInformation);
    }

    @Override
    public UserInformation getUser(String u_number) {
        return userDao.getUser(u_number);
    }

    @Override
    public int updateUpdateUploadImage(UserInformation userInformation) {
        return userDao.updateUpdateUploadImage(userInformation);
    }

    @Override
    public String getUserImage(String u_number) {
        return userDao.getUserImage(u_number);
    }

    @Override
    public int updateUserInformation(String u_name, String u_password, String u_number) {
        return userDao.updateUserInformation(u_name, u_password, u_number);
    }

    @Override
    public List<UserInformation> selectAllUsers(String u_number) {
        return userDao.selectAllUsers(u_number);
    }

    @Override
    public int deleteUser(String u_number) {
        return userDao.deleteUser(u_number);
    }

    @Override
    public int updateUser(UserInformation userInformation) {
        return userDao.updateUser(userInformation);
    }

    @Override
    public int updateUsers(String u_number, String u_password, String u_state) {
        return userDao.updateUsers(u_number, u_password, u_state);
    }

    @Override
    public void updateUserTime(String u_number, String u_time) {
        userDao.updateUserTime(u_number, u_time);
    }
}
