package cn.com.teacher.service;

import cn.com.teacher.bean.History;
import cn.com.teacher.bean.Resources;
import cn.com.teacher.bean.UserCollection;

import java.util.List;

/**
 * @author wx
 * @version 1.0
 * @date 2021/3/19 20:05
 */
public interface ResourcesService {

    /**
     * 返回所有的视频资源@return
     */
    List<Resources> getAllResources();

    /**
     * 传入标签内容条件@param r_content
     * 返回符合条件的集合@return
     */
    List<Resources> getSearchResources(String r_content);

    /**
     * 传入history对象@param history
     * 返回是否插入成功@return
     */
    int addResourcesHistory(History history);

    /**
     * 传入用户的id,根据用户id得到历史记录@param u_id
     * 返回所有符合条件的历史记录@return
     */
    List<History> getAllHistory(String u_id);

    /**
     * 传入查询的历史内容@param h_content
     * 传入要查询的历史内容所对应用户表的id@param h_forrign
     * 返回符合条件的历史记录@return
     */
    List<History> getSearchHistory(String h_content, String h_forrign);

    /**
     * 传入要已经收藏的对象@param userCollection
     * 返回是否插入成功@return
     */
    int addResourcesCollection(UserCollection userCollection);

    /**
     * 传入喜欢列表所对应的用户表id@param c_foreign
     * 返回喜欢的列表@return
     */
    List<UserCollection> getLoveResources(String c_foreign);

    /**
     * 传入收藏资源的路径@param c_path
     * 传入喜欢列表所对应的用户表id@param c_foreign
     * 返回这个路径是否存在@return
     */
    Integer checkLove(String c_path, String c_foreign);

    /**
     * 传入查询的内容@param c_content
     * 传入喜欢列表所对应的用户表id@param c_foreign
     * 返回符合条件的资源@return
     */
    List<UserCollection> getSearchLoveMovie(String c_content, String c_foreign);

    /**
     * 传入要删除的对象@param userCollection
     * 返回是否删除成功@return
     */
    int deleteLoveMovie(UserCollection userCollection);

    /**
     * 传入要删除历史记录所对应的时间信息@param h_time
     * 传入要删除历史记录所对应用户表的id@param h_forrign
     * 返回剩下历史记录的集合@return
     */
    int deleteHistory(String h_time, String h_forrign);

    /**
     * 传入查找资源表的视频路径@param r_path
     * 返回视频所对应的标签@return
     */
    Resources getLabel(String r_path);

    /**
     * 传入标签@param r_label
     * 返回符合标签所对应的视频资源信息@return
     */
    List<Resources> getRecommendMovie(String r_label);

    /**
     * 传入用户表的id@param h_forrign
     * 从历史表中查找数量最多的标签@return
     */
    History getRlabel(String h_forrign);


}
