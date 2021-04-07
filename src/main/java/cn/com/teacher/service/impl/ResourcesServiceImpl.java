package cn.com.teacher.service.impl;
import cn.com.teacher.bean.History;
import cn.com.teacher.bean.Resources;
import cn.com.teacher.dao.ResourcesDao;
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

    @Override
    public List<Resources> getSearchResources(String r_content) {
        return resourcesDao.getSearchResources(r_content);
    }
    @Override
    public int addResourcesHistory(History history) {
        return resourcesDao.addResourcesHistory(history);
    }

    @Override
    public List<History> getAllHistory() {
        return resourcesDao.getAllHistory();
    }
}
