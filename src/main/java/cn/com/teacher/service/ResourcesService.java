package cn.com.teacher.service;

import cn.com.teacher.bean.History;
import cn.com.teacher.bean.Resources;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author wx
 * @version 1.0
 * @date 2021/3/19 20:05
 */
public interface ResourcesService {

    /**
     *
     * 返回所有的用户@return
     */
    List<Resources> getAllResources();

    /**
     *
     * 返回指定的用户@return
     */
    List<Resources> getSearchResources(String r_content);

    /**
     *
     * 内容@param h_content
     * 路径@param h_path
     * 时间@param h_time
     * 返回视频集合@return
     */
    int addResourcesHistory(History history);
}
