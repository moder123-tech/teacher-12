package cn.com.teacher.service;

import cn.com.teacher.bean.UserInformation;
import org.springframework.stereotype.Service;


public interface UserService {

    /**
     * 添加用户信息
     * @param  userInformation  用户信息
     * @return 用户详细信息
     */
    int insertUser(UserInformation userInformation);


}
