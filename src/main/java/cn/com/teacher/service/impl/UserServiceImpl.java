package cn.com.teacher.service.impl;

import cn.com.teacher.bean.UserInformation;
import cn.com.teacher.dao.UserDao;
import cn.com.teacher.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


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
    public String getUser(String u_number, String u_password) {
        return userDao.getUser(u_number, u_password);
    }

    @Override
    public int updateUpdateUploadImage(UserInformation userInformation) {
        return userDao.updateUpdateUploadImage(userInformation);
    }

    @Override
    public String getUserImage(String u_number) {
        return userDao.getUserImage(u_number);
    }
}
