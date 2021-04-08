package cn.com.teacher.service;
import cn.com.teacher.bean.History;
import cn.com.teacher.bean.Resources;

import java.util.List;

/**
 * @author wx
 * @version 1.0
 * @date 2021/3/19 20:05
 */
public interface ResourcesService {

    /**
     *
     * 返回所有的视频资源@return
     */
    List<Resources> getAllResources();

    /**
     *
     * 传入标签内容条件@param r_content
     * 返回符合条件的集合@return
     */
    List<Resources> getSearchResources(String r_content);

    /**
     *
     * 传入history对象@param history
     * 返回是否插入成功@return
     */
    int addResourcesHistory(History history);

    /**
     *
     * 返回所有的历史记录@return
     */
    List<History> getAllHistory();

    /**
     *
     * 传入查询的历史内容@param h_content
     * 返回符合条件的历史记录@return
     */
    List<History> getSearchHistory(String h_content);

}
