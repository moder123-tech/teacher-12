package cn.com.teacher.service;

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
     * 返回所有的用户@return
     */
    List<Resources> getAllResources();
}
