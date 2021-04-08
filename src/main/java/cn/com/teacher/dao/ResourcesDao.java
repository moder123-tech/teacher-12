package cn.com.teacher.dao;

import cn.com.teacher.bean.History;
import cn.com.teacher.bean.Resources;
import cn.com.teacher.bean.UserCollection;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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

    /**
     *
     * 传入要已经收藏的对象@param userCollection
     * 返回是否插入成功@return
     */
    int addResourcesCollection(UserCollection userCollection);

    /**
     *
     * 返回喜欢的列表@return
     */
    List<UserCollection> getLoveResources();

    /**
     *
     * 传入收藏资源的路径@param c_path
     * 返回这个路径是否存在@return
     */
    Integer checkLove(String c_path);

    /**
     *
     * 传入查询的内容@param c_content
     * 返回符合条件的资源@return
     */
    List<UserCollection> getSearchLoveMovie(String c_content);

    /**
     *
     * 传入要删除的对象@param userCollection
     * 返回是否删除成功@return
     */
    int deleteLoveMovie(UserCollection userCollection);
}
