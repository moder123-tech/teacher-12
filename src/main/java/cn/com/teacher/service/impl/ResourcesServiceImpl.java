package cn.com.teacher.service.impl;

import cn.com.teacher.bean.History;
import cn.com.teacher.bean.Resources;
import cn.com.teacher.bean.UserCollection;
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

    @Override
    public List<History> getSearchHistory(String h_content) {
        return resourcesDao.getSearchHistory(h_content);
    }

    @Override
    public int addResourcesCollection(UserCollection userCollection) {
        return resourcesDao.addResourcesCollection(userCollection);
    }

    @Override
    public List<UserCollection> getLoveResources() {
        return resourcesDao.getLoveResources();
    }

    @Override
    public Integer checkLove(String c_path) {
        return resourcesDao.checkLove(c_path);
    }

    @Override
    public List<UserCollection> getSearchLoveMovie(String c_content) {
        return resourcesDao.getSearchLoveMovie(c_content);
    }

    @Override
    public int deleteLoveMovie(UserCollection userCollection) {
        return resourcesDao.deleteLoveMovie(userCollection);
    }

    @Override
    public int deleteHistory(String h_time) {
        return resourcesDao.deleteHistory(h_time);
    }


}
