package cn.com.teacher.service;

import cn.com.teacher.bean.Resources;

import java.util.List;

/**
 * @author : wx  //作者
 * @version 1.0 //版本
 * @date: 2021-03-27 14:15 //日期
 */
public interface TypeResourcesService {

    /**
     *
     * 返回所有的用户@return
     */
    List<Resources> getTypeResources(String type);
}
