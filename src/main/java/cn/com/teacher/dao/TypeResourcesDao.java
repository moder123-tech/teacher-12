package cn.com.teacher.dao;

import cn.com.teacher.bean.Resources;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author : wx  //作者
 * @version 1.0 //版本
 * @date: 2021-03-27 14:18 //日期
 */
@Mapper
public interface TypeResourcesDao {

    /**
     *
     * 返回所有的用户@return
     */
    List<Resources> getTypeResources(@Param("r_label") String r_label);
}
