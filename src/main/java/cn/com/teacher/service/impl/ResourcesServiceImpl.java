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
    public List<History> getAllHistory(String u_id) {
        return resourcesDao.getAllHistory(u_id);
    }

    @Override
    public List<History> getSearchHistory(String h_content, String h_forrign) {
        return resourcesDao.getSearchHistory(h_content, h_forrign);
    }

    @Override
    public int addResourcesCollection(UserCollection userCollection) {
        return resourcesDao.addResourcesCollection(userCollection);
    }

    @Override
    public List<UserCollection> getLoveResources(String c_foreign) {
        return resourcesDao.getLoveResources(c_foreign);
    }

    @Override
    public Integer checkLove(String c_path, String c_foreign) {
        return resourcesDao.checkLove(c_path, c_foreign);
    }

    @Override
    public List<UserCollection> getSearchLoveMovie(String c_content, String c_foreign) {
        return resourcesDao.getSearchLoveMovie(c_content, c_foreign);
    }

    @Override
    public int deleteLoveMovie(UserCollection userCollection) {
        return resourcesDao.deleteLoveMovie(userCollection);
    }

    @Override
    public int deleteHistory(String h_time, String h_forrign) {
        return resourcesDao.deleteHistory(h_time, h_forrign);
    }

    @Override
    public Resources getLabel(String r_path) {
        return resourcesDao.getLabel(r_path);
    }

    @Override
    public List<Resources> getRecommendMovie(String r_label) {
        return resourcesDao.getRecommendMovie(r_label);
    }

    @Override
    public History getRlabel(String h_forrign) {
        return resourcesDao.getRlabel(h_forrign);
    }

    @Override
    public List<History> getSomeHistory(History history) {
        return resourcesDao.getSomeHistory(history);
    }


}
