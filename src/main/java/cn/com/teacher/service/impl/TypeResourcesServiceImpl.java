package cn.com.teacher.service.impl;

import cn.com.teacher.bean.Resources;
import cn.com.teacher.dao.TypeResourcesDao;
import cn.com.teacher.service.TypeResourcesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : wx  //作者
 * @version 1.0 //版本
 * @date: 2021-03-27 14:16 //日期
 */
@Service("TypeResourcesService")
public class TypeResourcesServiceImpl implements TypeResourcesService {

    @Autowired
    private TypeResourcesDao typeResourcesDao;

    @Override
    public List<Resources> getTypeResources(String r_label) {
        return typeResourcesDao.getTypeResources(r_label);
    }
}
