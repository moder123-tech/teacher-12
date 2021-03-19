package cn.com.teacher.service.impl;

import cn.com.teacher.bean.Resources;
import cn.com.teacher.dao.ResourcesDao;
import cn.com.teacher.dao.UserDao;
import cn.com.teacher.service.ResourcesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wx
 * @version 1.0
 * @date 2021/3/19 20:06
 */
@Service("ResourcesService")
public class ResourcesServiceImpl implements ResourcesService {

    @Autowired
    public ResourcesDao resourcesDao;

    @Override
    public List<Resources> getAllResources() {
        return resourcesDao.getAllResources();
    }
}
