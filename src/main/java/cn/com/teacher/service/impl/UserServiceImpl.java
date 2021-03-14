package cn.com.teacher.service.impl;


import cn.com.teacher.bean.UserInformation;
import cn.com.teacher.dao.UserDao;
import cn.com.teacher.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Administrator
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Resource
    public UserDao userDao;

    @Override
    public int insertUser(UserInformation userInformation) {
        return userDao.insertUser(userInformation);
    }
}
