package cn.com.teacher.dao;

import cn.com.teacher.bean.Resources;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author wx
 * @version 1.0
 * @date 2021/3/19 20:01
 */
@Mapper
public interface ResourcesDao {

    /**
     *
     * 返回所有的用户@return
     */
    List<Resources> getAllResources();
}
